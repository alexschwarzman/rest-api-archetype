package 	com.aschwarzman.api.infrastructure.rql;

import com.aschwarzman.api.infrastructure.rql.context.QueryPart;
import com.aschwarzman.api.infrastructure.rql.context.condition.Condition;
import com.aschwarzman.api.infrastructure.rql.context.select.Select;
import com.aschwarzman.api.infrastructure.rql.context.sort.Sort;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class QueryContext {

	private Select select;
	private Condition condition;
	private Sort sort;

	public Select getSelect() {
		return select;
	}

	QueryContext withSelect(Select select) {
		this.select = select;
		return this;
	}

	public Condition getCondition() {
		return condition;
	}

	QueryContext withCondition(Condition condition) {
		if (this.condition != null) {
			this.condition = this.condition.add(condition);
		} else {
			this.condition = condition;
		}
		return this;
	}

	public Sort getSort() {
		return sort;
	}

	QueryContext withSort(Sort sort) {
		this.sort = sort;
		return this;
	}

	QueryContext with(QueryPart part) {
		if (part instanceof Select) {
			return withSelect((Select)part);
		} else if (part instanceof Condition) {
			return withCondition((Condition)part);
		} else if (part instanceof Sort) {
			return withSort((Sort)part);
		} else {
			throw new IllegalArgumentException("Unsupported query part");
		}
	}

	@Override
	public String toString() {
		return "QueryContext [select=" + select + ", condition=" + condition + ", sort=" + sort + "]";
	}

}
