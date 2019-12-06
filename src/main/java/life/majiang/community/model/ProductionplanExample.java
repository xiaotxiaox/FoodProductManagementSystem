package life.majiang.community.model;

import java.util.ArrayList;
import java.util.List;

public class ProductionplanExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public ProductionplanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
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
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productionplan
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productionplan
     *
     * @mbg.generated
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTimecreateIsNull() {
            addCriterion("timecreate is null");
            return (Criteria) this;
        }

        public Criteria andTimecreateIsNotNull() {
            addCriterion("timecreate is not null");
            return (Criteria) this;
        }

        public Criteria andTimecreateEqualTo(String value) {
            addCriterion("timecreate =", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateNotEqualTo(String value) {
            addCriterion("timecreate <>", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateGreaterThan(String value) {
            addCriterion("timecreate >", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateGreaterThanOrEqualTo(String value) {
            addCriterion("timecreate >=", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateLessThan(String value) {
            addCriterion("timecreate <", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateLessThanOrEqualTo(String value) {
            addCriterion("timecreate <=", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateLike(String value) {
            addCriterion("timecreate like", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateNotLike(String value) {
            addCriterion("timecreate not like", value, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateIn(List<String> values) {
            addCriterion("timecreate in", values, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateNotIn(List<String> values) {
            addCriterion("timecreate not in", values, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateBetween(String value1, String value2) {
            addCriterion("timecreate between", value1, value2, "timecreate");
            return (Criteria) this;
        }

        public Criteria andTimecreateNotBetween(String value1, String value2) {
            addCriterion("timecreate not between", value1, value2, "timecreate");
            return (Criteria) this;
        }

        public Criteria andNeednumIsNull() {
            addCriterion("neednum is null");
            return (Criteria) this;
        }

        public Criteria andNeednumIsNotNull() {
            addCriterion("neednum is not null");
            return (Criteria) this;
        }

        public Criteria andNeednumEqualTo(Integer value) {
            addCriterion("neednum =", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumNotEqualTo(Integer value) {
            addCriterion("neednum <>", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumGreaterThan(Integer value) {
            addCriterion("neednum >", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumGreaterThanOrEqualTo(Integer value) {
            addCriterion("neednum >=", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumLessThan(Integer value) {
            addCriterion("neednum <", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumLessThanOrEqualTo(Integer value) {
            addCriterion("neednum <=", value, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumIn(List<Integer> values) {
            addCriterion("neednum in", values, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumNotIn(List<Integer> values) {
            addCriterion("neednum not in", values, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumBetween(Integer value1, Integer value2) {
            addCriterion("neednum between", value1, value2, "neednum");
            return (Criteria) this;
        }

        public Criteria andNeednumNotBetween(Integer value1, Integer value2) {
            addCriterion("neednum not between", value1, value2, "neednum");
            return (Criteria) this;
        }

        public Criteria andTimelastestIsNull() {
            addCriterion("timelastest is null");
            return (Criteria) this;
        }

        public Criteria andTimelastestIsNotNull() {
            addCriterion("timelastest is not null");
            return (Criteria) this;
        }

        public Criteria andTimelastestEqualTo(String value) {
            addCriterion("timelastest =", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestNotEqualTo(String value) {
            addCriterion("timelastest <>", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestGreaterThan(String value) {
            addCriterion("timelastest >", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestGreaterThanOrEqualTo(String value) {
            addCriterion("timelastest >=", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestLessThan(String value) {
            addCriterion("timelastest <", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestLessThanOrEqualTo(String value) {
            addCriterion("timelastest <=", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestLike(String value) {
            addCriterion("timelastest like", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestNotLike(String value) {
            addCriterion("timelastest not like", value, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestIn(List<String> values) {
            addCriterion("timelastest in", values, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestNotIn(List<String> values) {
            addCriterion("timelastest not in", values, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestBetween(String value1, String value2) {
            addCriterion("timelastest between", value1, value2, "timelastest");
            return (Criteria) this;
        }

        public Criteria andTimelastestNotBetween(String value1, String value2) {
            addCriterion("timelastest not between", value1, value2, "timelastest");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTimeproduceIsNull() {
            addCriterion("timeproduce is null");
            return (Criteria) this;
        }

        public Criteria andTimeproduceIsNotNull() {
            addCriterion("timeproduce is not null");
            return (Criteria) this;
        }

        public Criteria andTimeproduceEqualTo(String value) {
            addCriterion("timeproduce =", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceNotEqualTo(String value) {
            addCriterion("timeproduce <>", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceGreaterThan(String value) {
            addCriterion("timeproduce >", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceGreaterThanOrEqualTo(String value) {
            addCriterion("timeproduce >=", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceLessThan(String value) {
            addCriterion("timeproduce <", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceLessThanOrEqualTo(String value) {
            addCriterion("timeproduce <=", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceLike(String value) {
            addCriterion("timeproduce like", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceNotLike(String value) {
            addCriterion("timeproduce not like", value, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceIn(List<String> values) {
            addCriterion("timeproduce in", values, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceNotIn(List<String> values) {
            addCriterion("timeproduce not in", values, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceBetween(String value1, String value2) {
            addCriterion("timeproduce between", value1, value2, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andTimeproduceNotBetween(String value1, String value2) {
            addCriterion("timeproduce not between", value1, value2, "timeproduce");
            return (Criteria) this;
        }

        public Criteria andPersonIsNull() {
            addCriterion("person is null");
            return (Criteria) this;
        }

        public Criteria andPersonIsNotNull() {
            addCriterion("person is not null");
            return (Criteria) this;
        }

        public Criteria andPersonEqualTo(Integer value) {
            addCriterion("person =", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotEqualTo(Integer value) {
            addCriterion("person <>", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThan(Integer value) {
            addCriterion("person >", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonGreaterThanOrEqualTo(Integer value) {
            addCriterion("person >=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThan(Integer value) {
            addCriterion("person <", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonLessThanOrEqualTo(Integer value) {
            addCriterion("person <=", value, "person");
            return (Criteria) this;
        }

        public Criteria andPersonIn(List<Integer> values) {
            addCriterion("person in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotIn(List<Integer> values) {
            addCriterion("person not in", values, "person");
            return (Criteria) this;
        }

        public Criteria andPersonBetween(Integer value1, Integer value2) {
            addCriterion("person between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andPersonNotBetween(Integer value1, Integer value2) {
            addCriterion("person not between", value1, value2, "person");
            return (Criteria) this;
        }

        public Criteria andGoodsidIsNull() {
            addCriterion("goodsid is null");
            return (Criteria) this;
        }

        public Criteria andGoodsidIsNotNull() {
            addCriterion("goodsid is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsidEqualTo(Integer value) {
            addCriterion("goodsid =", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotEqualTo(Integer value) {
            addCriterion("goodsid <>", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidGreaterThan(Integer value) {
            addCriterion("goodsid >", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsid >=", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidLessThan(Integer value) {
            addCriterion("goodsid <", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidLessThanOrEqualTo(Integer value) {
            addCriterion("goodsid <=", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidIn(List<Integer> values) {
            addCriterion("goodsid in", values, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotIn(List<Integer> values) {
            addCriterion("goodsid not in", values, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidBetween(Integer value1, Integer value2) {
            addCriterion("goodsid between", value1, value2, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsid not between", value1, value2, "goodsid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productionplan
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table productionplan
     *
     * @mbg.generated
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