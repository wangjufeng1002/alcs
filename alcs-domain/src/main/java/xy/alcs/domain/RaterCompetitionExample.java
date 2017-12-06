package xy.alcs.domain;

import java.util.ArrayList;
import java.util.List;

public class RaterCompetitionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RaterCompetitionExample() {
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

        public Criteria andRcIdIsNull() {
            addCriterion("rc_id is null");
            return (Criteria) this;
        }

        public Criteria andRcIdIsNotNull() {
            addCriterion("rc_id is not null");
            return (Criteria) this;
        }

        public Criteria andRcIdEqualTo(Long value) {
            addCriterion("rc_id =", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdNotEqualTo(Long value) {
            addCriterion("rc_id <>", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdGreaterThan(Long value) {
            addCriterion("rc_id >", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rc_id >=", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdLessThan(Long value) {
            addCriterion("rc_id <", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdLessThanOrEqualTo(Long value) {
            addCriterion("rc_id <=", value, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdIn(List<Long> values) {
            addCriterion("rc_id in", values, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdNotIn(List<Long> values) {
            addCriterion("rc_id not in", values, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdBetween(Long value1, Long value2) {
            addCriterion("rc_id between", value1, value2, "rcId");
            return (Criteria) this;
        }

        public Criteria andRcIdNotBetween(Long value1, Long value2) {
            addCriterion("rc_id not between", value1, value2, "rcId");
            return (Criteria) this;
        }

        public Criteria andRaterIdIsNull() {
            addCriterion("rater_id is null");
            return (Criteria) this;
        }

        public Criteria andRaterIdIsNotNull() {
            addCriterion("rater_id is not null");
            return (Criteria) this;
        }

        public Criteria andRaterIdEqualTo(Integer value) {
            addCriterion("rater_id =", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdNotEqualTo(Integer value) {
            addCriterion("rater_id <>", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdGreaterThan(Integer value) {
            addCriterion("rater_id >", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rater_id >=", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdLessThan(Integer value) {
            addCriterion("rater_id <", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdLessThanOrEqualTo(Integer value) {
            addCriterion("rater_id <=", value, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdIn(List<Integer> values) {
            addCriterion("rater_id in", values, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdNotIn(List<Integer> values) {
            addCriterion("rater_id not in", values, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdBetween(Integer value1, Integer value2) {
            addCriterion("rater_id between", value1, value2, "raterId");
            return (Criteria) this;
        }

        public Criteria andRaterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rater_id not between", value1, value2, "raterId");
            return (Criteria) this;
        }

        public Criteria andContestIdIsNull() {
            addCriterion("contest_id is null");
            return (Criteria) this;
        }

        public Criteria andContestIdIsNotNull() {
            addCriterion("contest_id is not null");
            return (Criteria) this;
        }

        public Criteria andContestIdEqualTo(Long value) {
            addCriterion("contest_id =", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdNotEqualTo(Long value) {
            addCriterion("contest_id <>", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdGreaterThan(Long value) {
            addCriterion("contest_id >", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdGreaterThanOrEqualTo(Long value) {
            addCriterion("contest_id >=", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdLessThan(Long value) {
            addCriterion("contest_id <", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdLessThanOrEqualTo(Long value) {
            addCriterion("contest_id <=", value, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdIn(List<Long> values) {
            addCriterion("contest_id in", values, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdNotIn(List<Long> values) {
            addCriterion("contest_id not in", values, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdBetween(Long value1, Long value2) {
            addCriterion("contest_id between", value1, value2, "contestId");
            return (Criteria) this;
        }

        public Criteria andContestIdNotBetween(Long value1, Long value2) {
            addCriterion("contest_id not between", value1, value2, "contestId");
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