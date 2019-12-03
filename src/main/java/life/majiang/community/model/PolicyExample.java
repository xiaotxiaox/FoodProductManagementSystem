package life.majiang.community.model;

import java.util.ArrayList;
import java.util.List;

public class PolicyExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public PolicyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("RATE is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("RATE is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Integer value) {
            addCriterion("RATE =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Integer value) {
            addCriterion("RATE <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Integer value) {
            addCriterion("RATE >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("RATE >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Integer value) {
            addCriterion("RATE <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Integer value) {
            addCriterion("RATE <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Integer> values) {
            addCriterion("RATE in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Integer> values) {
            addCriterion("RATE not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Integer value1, Integer value2) {
            addCriterion("RATE between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Integer value1, Integer value2) {
            addCriterion("RATE not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNull() {
            addCriterion("HANDLER is null");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNotNull() {
            addCriterion("HANDLER is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerEqualTo(Integer value) {
            addCriterion("HANDLER =", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotEqualTo(Integer value) {
            addCriterion("HANDLER <>", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThan(Integer value) {
            addCriterion("HANDLER >", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThanOrEqualTo(Integer value) {
            addCriterion("HANDLER >=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThan(Integer value) {
            addCriterion("HANDLER <", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThanOrEqualTo(Integer value) {
            addCriterion("HANDLER <=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerIn(List<Integer> values) {
            addCriterion("HANDLER in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotIn(List<Integer> values) {
            addCriterion("HANDLER not in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerBetween(Integer value1, Integer value2) {
            addCriterion("HANDLER between", value1, value2, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotBetween(Integer value1, Integer value2) {
            addCriterion("HANDLER not between", value1, value2, "handler");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("DISCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("DISCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("DISCOUNT =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("DISCOUNT <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("DISCOUNT >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISCOUNT >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("DISCOUNT <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("DISCOUNT <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("DISCOUNT in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("DISCOUNT not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("DISCOUNT between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("DISCOUNT not between", value1, value2, "discount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table POLICY
     *
     * @mbg.generated do_not_delete_during_merge Tue Dec 03 09:18:02 GMT+08:00 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table POLICY
     *
     * @mbg.generated Tue Dec 03 09:18:02 GMT+08:00 2019
     */
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