package xy.alcs.domain;

import java.util.ArrayList;
import java.util.List;

public class MajorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MajorExample() {
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

        public Criteria andMajIdIsNull() {
            addCriterion("maj_id is null");
            return (Criteria) this;
        }

        public Criteria andMajIdIsNotNull() {
            addCriterion("maj_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajIdEqualTo(Integer value) {
            addCriterion("maj_id =", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdNotEqualTo(Integer value) {
            addCriterion("maj_id <>", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdGreaterThan(Integer value) {
            addCriterion("maj_id >", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("maj_id >=", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdLessThan(Integer value) {
            addCriterion("maj_id <", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdLessThanOrEqualTo(Integer value) {
            addCriterion("maj_id <=", value, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdIn(List<Integer> values) {
            addCriterion("maj_id in", values, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdNotIn(List<Integer> values) {
            addCriterion("maj_id not in", values, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdBetween(Integer value1, Integer value2) {
            addCriterion("maj_id between", value1, value2, "majId");
            return (Criteria) this;
        }

        public Criteria andMajIdNotBetween(Integer value1, Integer value2) {
            addCriterion("maj_id not between", value1, value2, "majId");
            return (Criteria) this;
        }

        public Criteria andMajNameIsNull() {
            addCriterion("maj_name is null");
            return (Criteria) this;
        }

        public Criteria andMajNameIsNotNull() {
            addCriterion("maj_name is not null");
            return (Criteria) this;
        }

        public Criteria andMajNameEqualTo(String value) {
            addCriterion("maj_name =", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameNotEqualTo(String value) {
            addCriterion("maj_name <>", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameGreaterThan(String value) {
            addCriterion("maj_name >", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameGreaterThanOrEqualTo(String value) {
            addCriterion("maj_name >=", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameLessThan(String value) {
            addCriterion("maj_name <", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameLessThanOrEqualTo(String value) {
            addCriterion("maj_name <=", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameLike(String value) {
            addCriterion("maj_name like", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameNotLike(String value) {
            addCriterion("maj_name not like", value, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameIn(List<String> values) {
            addCriterion("maj_name in", values, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameNotIn(List<String> values) {
            addCriterion("maj_name not in", values, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameBetween(String value1, String value2) {
            addCriterion("maj_name between", value1, value2, "majName");
            return (Criteria) this;
        }

        public Criteria andMajNameNotBetween(String value1, String value2) {
            addCriterion("maj_name not between", value1, value2, "majName");
            return (Criteria) this;
        }

        public Criteria andCollegeIdIsNull() {
            addCriterion("college_id is null");
            return (Criteria) this;
        }

        public Criteria andCollegeIdIsNotNull() {
            addCriterion("college_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeIdEqualTo(Integer value) {
            addCriterion("college_id =", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdNotEqualTo(Integer value) {
            addCriterion("college_id <>", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdGreaterThan(Integer value) {
            addCriterion("college_id >", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("college_id >=", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdLessThan(Integer value) {
            addCriterion("college_id <", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdLessThanOrEqualTo(Integer value) {
            addCriterion("college_id <=", value, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdIn(List<Integer> values) {
            addCriterion("college_id in", values, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdNotIn(List<Integer> values) {
            addCriterion("college_id not in", values, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdBetween(Integer value1, Integer value2) {
            addCriterion("college_id between", value1, value2, "collegeId");
            return (Criteria) this;
        }

        public Criteria andCollegeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("college_id not between", value1, value2, "collegeId");
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