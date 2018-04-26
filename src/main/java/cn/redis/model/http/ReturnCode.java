package cn.redis.model.http;

public enum ReturnCode {
	
	IS_SUCCESS(0, "成功请求"),
	IS_NULL(1,"请求参数为空"),
	IS_FAIL(-1, "请求失败");
	
	private int codeInt;
	private String codeString;
	private ReturnCode(int codeInt, String codeString) {
		this.codeInt = codeInt;
		this.codeString = codeString;
	}
	
	/**
	 * 获取状态码
	 * @return
	 */
	public int getCodeInt() {
		return this.codeInt;
	}
	
	/**
	 * 获取状态内容
	 * @return
	 */
	public String getCodeString() {
		return this.codeString;
	}
}
