package life.majiang.community.model;

import java.util.ArrayList;
import java.util.List;

public class BatchExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public BatchExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
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
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
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

        public Criteria andGoodsnameIsNull() {
            addCriterion("goodsname is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNotNull() {
            addCriterion("goodsname is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameEqualTo(String value) {
            addCriterion("goodsname =", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotEqualTo(String value) {
            addCriterion("goodsname <>", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThan(String value) {
            addCriterion("goodsname >", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThanOrEqualTo(String value) {
            addCriterion("goodsname >=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThan(String value) {
            addCriterion("goodsname <", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThanOrEqualTo(String value) {
            addCriterion("goodsname <=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLike(String value) {
            addCriterion("goodsname like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotLike(String value) {
            addCriterion("goodsname not like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIn(List<String> values) {
            addCriterion("goodsname in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotIn(List<String> values) {
            addCriterion("goodsname not in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameBetween(String value1, String value2) {
            addCriterion("goodsname between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotBetween(String value1, String value2) {
            addCriterion("goodsname not between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnumIsNull() {
            addCriterion("goodsnum is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnumIsNotNull() {
            addCriterion("goodsnum is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnumEqualTo(Integer value) {
            addCriterion("goodsnum =", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumNotEqualTo(Integer value) {
            addCriterion("goodsnum <>", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumGreaterThan(Integer value) {
            addCriterion("goodsnum >", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsnum >=", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumLessThan(Integer value) {
            addCriterion("goodsnum <", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumLessThanOrEqualTo(Integer value) {
            addCriterion("goodsnum <=", value, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumIn(List<Integer> values) {
            addCriterion("goodsnum in", values, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumNotIn(List<Integer> values) {
            addCriterion("goodsnum not in", values, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumBetween(Integer value1, Integer value2) {
            addCriterion("goodsnum between", value1, value2, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andGoodsnumNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsnum not between", value1, value2, "goodsnum");
            return (Criteria) this;
        }

        public Criteria andTimefinishIsNull() {
            addCriterion("timefinish is null");
            return (Criteria) this;
        }

        public Criteria andTimefinishIsNotNull() {
            addCriterion("timefinish is not null");
            return (Criteria) this;
        }

        public Criteria andTimefinishEqualTo(String value) {
            addCriterion("timefinish =", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishNotEqualTo(String value) {
            addCriterion("timefinish <>", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishGreaterThan(String value) {
            addCriterion("timefinish >", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishGreaterThanOrEqualTo(String value) {
            addCriterion("timefinish >=", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishLessThan(String value) {
            addCriterion("timefinish <", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishLessThanOrEqualTo(String value) {
            addCriterion("timefinish <=", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishLike(String value) {
            addCriterion("timefinish like", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishNotLike(String value) {
            addCriterion("timefinish not like", value, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishIn(List<String> values) {
            addCriterion("timefinish in", values, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishNotIn(List<String> values) {
            addCriterion("timefinish not in", values, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishBetween(String value1, String value2) {
            addCriterion("timefinish between", value1, value2, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimefinishNotBetween(String value1, String value2) {
            addCriterion("timefinish not between", value1, value2, "timefinish");
            return (Criteria) this;
        }

        public Criteria andTimeprotectIsNull() {
            addCriterion("timeprotect is null");
            return (Criteria) this;
        }

        public Criteria andTimeprotectIsNotNull() {
            addCriterion("timeprotect is not null");
            return (Criteria) this;
        }

        public Criteria andTimeprotectEqualTo(String value) {
            addCriterion("timeprotect =", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectNotEqualTo(String value) {
            addCriterion("timeprotect <>", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectGreaterThan(String value) {
            addCriterion("timeprotect >", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectGreaterThanOrEqualTo(String value) {
            addCriterion("timeprotect >=", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectLessThan(String value) {
            addCriterion("timeprotect <", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectLessThanOrEqualTo(String value) {
            addCriterion("timeprotect <=", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectLike(String value) {
            addCriterion("timeprotect like", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectNotLike(String value) {
            addCriterion("timeprotect not like", value, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectIn(List<String> values) {
            addCriterion("timeprotect in", values, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectNotIn(List<String> values) {
            addCriterion("timeprotect not in", values, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectBetween(String value1, String value2) {
            addCriterion("timeprotect between", value1, value2, "timeprotect");
            return (Criteria) this;
        }

        public Criteria andTimeprotectNotBetween(String value1, String value2) {
            addCriterion("timeprotect not between", value1, value2, "timeprotect");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table batch
     *
     * @mbg.generated do_not_delete_during_merge Thu Dec 05 10:52:54 GMT+08:00 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table batch
     *
     * @mbg.generated Thu Dec 05 10:52:54 GMT+08:00 2019
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