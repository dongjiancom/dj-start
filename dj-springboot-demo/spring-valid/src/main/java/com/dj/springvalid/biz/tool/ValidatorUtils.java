package com.dj.springvalid.biz.tool;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * 参考文档：http://docs.jboss.org/hibernate/validator/6.0/reference/en-US/html_single/
 * created by riger on 2020/11/21
 */
public class ValidatorUtils {
    public static Validator validator;
    static {
        validator = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    /**
     * 静态方法校验使用的
     *
     * @param object
     * @return
     */
    public static void validate(Object object) {
        if(object == null){
            throw new RuntimeException("参数不完整");
        }
        Set<ConstraintViolation<Object>> validate = validator.validate(object);
        resultValidate(validate);
    }

    private static void resultValidate(Set<ConstraintViolation<Object>> validate) {
        if (!validate.isEmpty()) {
            final StringBuffer stringBuffer = new StringBuffer();
            validate.stream().forEach(
                    item -> stringBuffer.append(item.getMessage()).append(","));
            stringBuffer.setLength(stringBuffer.length() - 1);
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups){
        Validator validator =
                Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder sbPath = new StringBuilder();
            StringBuilder sbMessage = new StringBuilder();
            constraintViolations.stream().map(ConstraintViolation::getPropertyPath).forEach(s -> sbPath.append(s).append(";"));
            constraintViolations.stream().map(ConstraintViolation::getMessage).forEach(s -> sbMessage.append(s).append(";"));

            throw new RuntimeException(sbPath.toString() + sbMessage);
        }
    }
}
