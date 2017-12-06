package xy.alcs.domain;

import java.util.ArrayList;
import java.util.List;

public class RaterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RaterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRidIsNull() {
            addCriterion("rid is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("rid is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(Integer value) {
            addCriterion("rid =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(Integer value) {
            addCriterion("rid <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(Integer value) {
            addCriterion("rid >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rid >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(Integer value) {
            addCriterion("rid <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(Integer value) {
            addCriterion("rid <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<Integer> values) {
            addCriterion("rid in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<Integer> values) {
            addCriterion("rid not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(Integer value1, Integer value2) {
            addCriterion("rid between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(Integer value1, Integer value2) {
            addCriterion("rid not between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRatNameIsNull() {
            addCriterion("rat_name is null");
            return (Criteria) this;
        }

        public Criteria andRatNameIsNotNull() {
            addCriterion("rat_name is not null");
            return (Criteria) this;
        }

        public Criteria andRatNameEqualTo(String value) {
            addCriterion("rat_name =", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameNotEqualTo(String value) {
            addCriterion("rat_name <>", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameGreaterThan(String value) {
            addCriterion("rat_name >", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameGreaterThanOrEqualTo(String value) {
            addCriterion("rat_name >=", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameLessThan(String value) {
            addCriterion("rat_name <", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameLessThanOrEqualTo(String value) {
            addCriterion("rat_name <=", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameLike(String value) {
            addCriterion("rat_name like", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameNotLike(String value) {
            addCriterion("rat_name not like", value, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameIn(List<String> values) {
            addCriterion("rat_name in", values, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameNotIn(List<String> values) {
            addCriterion("rat_name not in", values, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameBetween(String value1, String value2) {
            addCriterion("rat_name between", value1, value2, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatNameNotBetween(String value1, String value2) {
            addCriterion("rat_name not between", value1, value2, "ratName");
            return (Criteria) this;
        }

        public Criteria andRatAccountIsNull() {
            addCriterion("rat_account is null");
            return (Criteria) this;
        }

        public Criteria andRatAccountIsNotNull() {
            addCriterion("rat_account is not null");
            return (Criteria) this;
        }

        public Criteria andRatAccountEqualTo(String value) {
            addCriterion("rat_account =", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountNotEqualTo(String value) {
            addCriterion("rat_account <>", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountGreaterThan(String value) {
            addCriterion("rat_account >", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountGreaterThanOrEqualTo(String value) {
            addCriterion("rat_account >=", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountLessThan(String value) {
            addCriterion("rat_account <", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountLessThanOrEqualTo(String value) {
            addCriterion("rat_account <=", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountLike(String value) {
            addCriterion("rat_account like", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountNotLike(String value) {
            addCriterion("rat_account not like", value, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountIn(List<String> values) {
            addCriterion("rat_account in", values, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountNotIn(List<String> values) {
            addCriterion("rat_account not in", values, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountBetween(String value1, String value2) {
            addCriterion("rat_account between", value1, value2, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatAccountNotBetween(String value1, String value2) {
            addCriterion("rat_account not between", value1, value2, "ratAccount");
            return (Criteria) this;
        }

        public Criteria andRatPasswordIsNull() {
            addCriterion("rat_password is null");
            return (Criteria) this;
        }

        public Criteria andRatPasswordIsNotNull() {
            addCriterion("rat_password is not null");
            return (Criteria) this;
        }

        public Criteria andRatPasswordEqualTo(String value) {
            addCriterion("rat_password =", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordNotEqualTo(String value) {
            addCriterion("rat_password <>", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordGreaterThan(String value) {
            addCriterion("rat_password >", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("rat_password >=", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordLessThan(String value) {
            addCriterion("rat_password <", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordLessThanOrEqualTo(String value) {
            addCriterion("rat_password <=", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordLike(String value) {
            addCriterion("rat_password like", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordNotLike(String value) {
            addCriterion("rat_password not like", value, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordIn(List<String> values) {
            addCriterion("rat_password in", values, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordNotIn(List<String> values) {
            addCriterion("rat_password not in", values, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordBetween(String value1, String value2) {
            addCriterion("rat_password between", value1, value2, "ratPassword");
            return (Criteria) this;
        }

        public Criteria andRatPasswordNotBetween(String value1, String value2) {
            addCriterion("rat_password not between", value1, value2, "ratPassword");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}