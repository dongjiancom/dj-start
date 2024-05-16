package com.dj.springvalid.biz.tool;


import cn.hutool.core.util.StrUtil;
import dj.com.tool.BeanTool;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-11-22 10:34
 **/
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckAtLeastOneNotNull.CheckAtLeastOneNotNullValidator.class)
@Documented
public @interface CheckAtLeastOneNotNull {

    String message() default "not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fieldNames();

    class CheckAtLeastOneNotNullValidator implements ConstraintValidator<CheckAtLeastOneNotNull, Object> {

        private String[] fieldNames;

        @Override
        public void initialize(CheckAtLeastOneNotNull constraintAnnotation) {
            this.fieldNames = constraintAnnotation.fieldNames();
        }

        @Override
        public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {

            if (object == null) {
                return true;
            }
            try {
                for (String fieldName : fieldNames) {
                    // 通过反射获取属性值
                    Object fieldValue = BeanTool.getFieldObjByName(object, fieldName);
                    if (!StrUtil.isBlankIfStr(fieldValue)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
