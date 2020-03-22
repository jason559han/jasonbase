package com.jason.base.tags.grid.annotation;

/**
 * 设置特定列的格式
 * @author jason558han
 * @date 2020年02月01日
 */
public @interface Formatoptions {

	/**
	 * 当formatter指定为integer、number、currency时，设置每千位分隔符
	 * @return 分隔符
	 */
	String thousandsSeparator() default "";

	/**
	 * 当formatter指定为integer、number、currency时，设置在没有数据的情况下的默认值
	 * @return 默认值
	 */
	String defaulValue() default "";

	/**
	 * 当formatter指定为number、currency时，设置小数位分隔符
	 * @return 小数分隔符
	 */
	String decimalSeparator() default "";

	/**
	 * 当formatter指定为number、currency时，设置小数位数
	 * @return 小数位
	 */
	String decimalPlaces() default "";

	/**
	 * 当formatter指定为currency时，设置在数据前面添加的文本比如货币符号”$” 
	 * @return 金额前置符
	 */
	String prefix() default "";

	/**
	 * 当formatter指定为currency时，设置在数据后面添加的文本比如货币符号”$” 
	 * @return 金额后置符
	 */
	String suffix() default "";

	/**
	 * 当formatter指定为date时，设置原格式 
	 * @return 原格式
	 */
	String srcformat() default "";

	/**
	 * 当formatter指定为date时，设置新输出格式 
	 * @return 输出格式
	 */
	String newformat() default "";

	/**
	 * 当formatter指定为link、showlink时，默认为empty如果设置,则创建一个以单元格的值和target属性的链接 
	 * @return 未测试，待用
	 */
	String target() default "";

	/**
	 * 当formatter指定为showlink、select时，设置链接 
	 * @return 基础链接
	 */
	String baseLinkUrl() default "";

	/**
	 * 当formatter指定为showlink时，设置附加值,在baseLinkUrl后面
	 * @return 链接
	 */
	String showAction() default "";

	/**
	 * 当formatter指定为showlink、select时，设置额外参数
	 * @return 参数
	 */
	String addParam() default "";

	/**
	 * 当formatter指定为showlink、select时，是第1个参数,之后由showAction补充,默认情况下,这是id
	 * @return 待测试
	 */
	String idName() default "";

	/**
	 * 当formatter指定为checkbox时，默认true禁止改变状态,如果设置为false是可以改变的 
	 * @return true或false
	 */
	boolean disabled() default true;

	/**
	 * 当formatter指定为actions时，默认按钮
	 * @return 待测试
	 */
	boolean keys() default false;
}
