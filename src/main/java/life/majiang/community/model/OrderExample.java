package life.majiang.community.model;

import java.util.ArrayList;
import java.util.List;

public class OrderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCustomIsNull() {
            addCriterion("custom is null");
            return (Criteria) this;
        }

        public Criteria andCustomIsNotNull() {
            addCriterion("custom is not null");
            return (Criteria) this;
        }

        public Criteria andCustomEqualTo(Integer value) {
            addCriterion("custom =", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomNotEqualTo(Integer value) {
            addCriterion("custom <>", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomGreaterThan(Integer value) {
            addCriterion("custom >", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomGreaterThanOrEqualTo(Integer value) {
            addCriterion("custom >=", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomLessThan(Integer value) {
            addCriterion("custom <", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomLessThanOrEqualTo(Integer value) {
            addCriterion("custom <=", value, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomIn(List<Integer> values) {
            addCriterion("custom in", values, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomNotIn(List<Integer> values) {
            addCriterion("custom not in", values, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomBetween(Integer value1, Integer value2) {
            addCriterion("custom between", value1, value2, "custom");
            return (Criteria) this;
        }

        public Criteria andCustomNotBetween(Integer value1, Integer value2) {
            addCriterion("custom not between", value1, value2, "custom");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNull() {
            addCriterion("goods is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNotNull() {
            addCriterion("goods is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEqualTo(Integer value) {
            addCriterion("goods =", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotEqualTo(Integer value) {
            addCriterion("goods <>", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThan(Integer value) {
            addCriterion("goods >", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods >=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThan(Integer value) {
            addCriterion("goods <", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThanOrEqualTo(Integer value) {
            addCriterion("goods <=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsIn(List<Integer> values) {
            addCriterion("goods in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotIn(List<Integer> values) {
            addCriterion("goods not in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsBetween(Integer value1, Integer value2) {
            addCriterion("goods between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotBetween(Integer value1, Integer value2) {
            addCriterion("goods not between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("order_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("order_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(String value) {
            addCriterion("order_date =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(String value) {
            addCriterion("order_date <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(String value) {
            addCriterion("order_date >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(String value) {
            addCriterion("order_date >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(String value) {
            addCriterion("order_date <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(String value) {
            addCriterion("order_date <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLike(String value) {
            addCriterion("order_date like", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotLike(String value) {
            addCriterion("order_date not like", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<String> values) {
            addCriterion("order_date in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<String> values) {
            addCriterion("order_date not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(String value1, String value2) {
            addCriterion("order_date between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(String value1, String value2) {
            addCriterion("order_date not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNull() {
            addCriterion("pick_date is null");
            return (Criteria) this;
        }

        public Criteria andPickDateIsNotNull() {
            addCriterion("pick_date is not null");
            return (Criteria) this;
        }

        public Criteria andPickDateEqualTo(String value) {
            addCriterion("pick_date =", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotEqualTo(String value) {
            addCriterion("pick_date <>", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThan(String value) {
            addCriterion("pick_date >", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateGreaterThanOrEqualTo(String value) {
            addCriterion("pick_date >=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThan(String value) {
            addCriterion("pick_date <", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLessThanOrEqualTo(String value) {
            addCriterion("pick_date <=", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateLike(String value) {
            addCriterion("pick_date like", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotLike(String value) {
            addCriterion("pick_date not like", value, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateIn(List<String> values) {
            addCriterion("pick_date in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotIn(List<String> values) {
            addCriterion("pick_date not in", values, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateBetween(String value1, String value2) {
            addCriterion("pick_date between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andPickDateNotBetween(String value1, String value2) {
            addCriterion("pick_date not between", value1, value2, "pickDate");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNull() {
            addCriterion("handler is null");
            return (Criteria) this;
        }

        public Criteria andHandlerIsNotNull() {
            addCriterion("handler is not null");
            return (Criteria) this;
        }

        public Criteria andHandlerEqualTo(Long value) {
            addCriterion("handler =", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotEqualTo(Long value) {
            addCriterion("handler <>", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThan(Long value) {
            addCriterion("handler >", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerGreaterThanOrEqualTo(Long value) {
            addCriterion("handler >=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThan(Long value) {
            addCriterion("handler <", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerLessThanOrEqualTo(Long value) {
            addCriterion("handler <=", value, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerIn(List<Long> values) {
            addCriterion("handler in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotIn(List<Long> values) {
            addCriterion("handler not in", values, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerBetween(Long value1, Long value2) {
            addCriterion("handler between", value1, value2, "handler");
            return (Criteria) this;
        }

        public Criteria andHandlerNotBetween(Long value1, Long value2) {
            addCriterion("handler not between", value1, value2, "handler");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_collection
     *
     * @mbg.generated do_not_delete_during_merge Wed Dec 04 17:02:58 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table order_collection
     *
     * @mbg.generated Wed Dec 04 17:02:58 CST 2019
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