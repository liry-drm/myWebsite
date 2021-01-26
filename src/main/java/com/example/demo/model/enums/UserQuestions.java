package com.example.demo.model.enums;

/**
 * 用户问题枚举类
 *
 * @author ASUS
 */
public enum UserQuestions {
    /**
     * 父亲的姓名
     */
    FARTHER("1", "我父亲的姓名?"),
    /**
     * 母亲的姓名
     */
    MOTHER("2", "我母亲的姓名?"),
    /**
     * 我的小学校名
     */
    PRIMARY_SCHOOL("3", "我的小学校名?"),
    /**
     * 我的大学校名
     */
    COLLAGE("4", "我的大学校名?"),
    /**
     * 我最爱的人
     */
    LOVER("5", "我最爱的人?"),
    /**
     * 我的出生地
     */
    BIRTHPLACE("6", "我的出生地?");

    private String code;
    
    private String name;

    UserQuestions(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 通过typeId获取对应的枚举对象
     *
     * @param code code
     * @return 枚举对象
     * @author Lry
     **/
    public static UserQuestions getUserQuestionByCode(String code) {
        for (UserQuestions userQuestion : UserQuestions.values()) {
            if (code.equals(userQuestion.code)) {
                return userQuestion;
            }
        }
        return null;
    }
}
