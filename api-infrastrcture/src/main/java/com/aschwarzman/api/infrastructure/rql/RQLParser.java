package com.aschwarzman.api.infrastructure.rql;

import com.aschwarzman.api.infrastructure.rql.context.ConstantExpression;
import com.aschwarzman.api.infrastructure.rql.context.Expression;
import com.aschwarzman.api.infrastructure.rql.context.QueryPart;
import com.aschwarzman.api.infrastructure.rql.context.condition.Condition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.EqCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.GtCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.GteCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.InCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.LtCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.LteCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.comparison.NotEqCondition;
import com.aschwarzman.api.infrastructure.rql.context.condition.logical.And;
import com.aschwarzman.api.infrastructure.rql.context.condition.logical.Or;
import com.aschwarzman.api.infrastructure.rql.context.select.Select;
import com.aschwarzman.api.infrastructure.rql.context.sort.Sort;
import com.aschwarzman.api.infrastructure.rql.context.sort.SortPart;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.jazdw.rql.parser.ASTNode;
import net.jazdw.rql.parser.SimpleASTVisitor;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class RQLParser {

	private final net.jazdw.rql.parser.RQLParser nativeParser = new net.jazdw.rql.parser.RQLParser();

	private final Map<String, String> fieldAliases;

	public RQLParser(Map<String, String> fieldAliases) {
		this.fieldAliases = Collections.unmodifiableMap(fieldAliases != null ? fieldAliases : Collections.emptyMap());
	}

	public QueryContext parse(String queryString) {

		return nativeParser.parse(queryString, new RQLVisitor(fieldAliases));

	}

	private static final class RQLVisitor implements SimpleASTVisitor<QueryContext> {

		private final Map<String, String> fieldAliases;

		private QueryContext query = new QueryContext();

		public RQLVisitor(Map<String, String> fieldAliases) {
			this.fieldAliases = fieldAliases;
		}

		public QueryContext visit(ASTNode node) {
			String name = node.getName();
			if (name.equals("and")) {
				List<Object> arguments = node.getArguments();
				for (Object argument : arguments) {
					if (argument instanceof ASTNode) {
						query.with(visitQueryPart((ASTNode)argument));
					} else {
						throw new IllegalArgumentException("illegal argument " + argument + " in '" + name + "' clause");
					}
				}
			} else {
				query.with(visitQueryPart(node));
			}
			return query;
		}

		private QueryPart visitQueryPart(ASTNode node) {
			String name = node.getName();
			if (name.equals("select")) {
				List<Object> arguments = node.getArguments();
				List<Expression> selectExpressions = new ArrayList();
				for (Object argument : arguments) {
					if (argument instanceof String) {
						selectExpressions.add(new ConstantExpression(visitFieldName((String)argument)));
					} else if (argument instanceof ASTNode) {
						// TODO add select function support
						throw new NotImplementedException("visit of " + argument + " not implemented");
					}
				}
				return new Select(selectExpressions);
			} else if (name.equals("sort")) {
				List<Object> arguments = node.getArguments();
				List<SortPart> parts = new ArrayList();
				for (Object argument : arguments) {
					if (argument instanceof String) {
						String rawPart = (String)argument;
						boolean asc = true;
						String fieldName = null;
						if (rawPart.startsWith("+")) {
							asc = true;
							fieldName = rawPart.substring(1);
						} else if (rawPart.startsWith("-")) {
							asc = false;
							fieldName = rawPart.substring(1);
						} else {
							asc = true;
							fieldName = rawPart;
						}
						parts.add(new SortPart(new ConstantExpression(visitFieldName(fieldName)), asc));
					} else if (argument instanceof ASTNode) {
						// TODO add select function support
						throw new NotImplementedException("visit of " + argument + " not implemented");
					}
				}
				return new Sort(parts);
			} else {
				// temporary assume that all remaining  node types are for the condition part
				return visitConditionTree(node);
			}
		}

		private Condition visitConditionTree(ASTNode node) {
			String name = node.getName();
			if (name.equals("and")) {
				List<Condition> conditions = new ArrayList();
				List<Object> arguments = node.getArguments();
				for (Object argument : arguments) {
					if (argument instanceof ASTNode) {
						conditions.add(visitConditionTree((ASTNode)argument));
					} else {
						throw new IllegalArgumentException("illegal argument " + argument + " in '" + name + "' clause");
					}
				}
				return new And(conditions);
			} else if (name.equals("or")) {
				List<Condition> conditions = new ArrayList();
				List<Object> arguments = node.getArguments();
				for (Object argument : arguments) {
					if (argument instanceof ASTNode) {
						conditions.add(visitConditionTree((ASTNode)argument));
					} else {
						throw new IllegalArgumentException("illegal argument " + argument + " in '" + name + "' clause");
					}
				}
				return new Or(conditions);
			} else if (name.equals("eq")) {
				return new EqCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("ne")) {
				return new NotEqCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("gt")) {
				return new GtCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("ge")) {
				return new GteCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("lt")) {
				return new LtCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("le")) {
				return new LteCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else if (name.equals("in")) {
				return new InCondition(visitFieldName((String)node.getArgument(0)), visitExpression(node.getArgument(1)));
			} else {
				throw new NotImplementedException("visit of " + name + " not implemented");
			}
		}

		private Expression visitExpression(Object argument) {
			if (argument instanceof ASTNode) {
				throw new NotImplementedException("visit of " + argument + " not implemented");
			} else {
				return new ConstantExpression(argument);
			}
		}

		private String visitFieldName(String fieldName) {
			String alias = fieldAliases.get(fieldName);
			if (alias == null) {
				alias = fieldName;
			}
			return alias;
		}

	}

}
