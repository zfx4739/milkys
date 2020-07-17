/**
 * 
 */
package com.example.SecurityDemo.util;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 返回前台的Result结果工具类
 * @author zfx
 * 2020年5月20日
 */
public class Result<T> {
    public static final int RESULT_SUCCESS = 1; //成功
    public static final int RESULT_ERROR = 0;   //失败
    public static final int SYSTEM_ERROR = 500; //系统错误
    public static final int TOKEN_EXPIRE = 403; //token过期
    public static final int TOKEN_OCCUPIED = 403;//token被占用

    //登录状态
    public static final int Login_Success = 200;//登录成功
    public static final int Login_Failure = 400;//登录失败
    public static final int Need_Authorities = 300;//无权访问返回
    public static final int Logout_Success = 100;//退出成功


    public static final int USERORPASS_ERROR = -97; //用户名或密码错误
    public static final int USERORPASS_EMPTY = -96; //用户名或密码为空
    public static final int COMPANYID_EMPTY = -95; //companyId为空



    private Integer code;                          //状态码
	private String message;                        //状态提示语
	private T data;                                //数据
	private long total;                             //总条数
	private long size;                              //当前页总条数
	private long current;                            //当前页
	private long pages;                              //总页数

	private String error;
	private String success;
	Object principal;

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the mesage
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 获取子集通用方法
	 * 
	 * @param list
	 *            集合
	 * @param parentId
	 *            父类id
	 * @param getParMeth
	 *            get父类id方法名
	 * @param setChildMeth
	 *            set子类集合方法名
	 * @param getIdMeth
	 *            getId方法名
	 * @return
	 */
	
	public static <T> List<T> getChild(List<T> list, String parentId, String getParMeth, String setChildMeth,
			String getIdMeth) {
		List<T> children = new ArrayList<>();
		for (T t : list) {
			try {
				if (t.getClass().getMethod(getParMeth).invoke(t).toString().equals(parentId)) {
					t.getClass().getDeclaredMethod(setChildMeth, List.class).invoke(t,
							getChild(list, t.getClass().getMethod(getIdMeth).invoke(t).toString(), getParMeth,
									setChildMeth, getIdMeth));
					children.add(t);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return children;
	}

	public static <T> Object prefixPicture(Collection<? extends T> list, T x, String getPictureMeth, String setPictureMeth) {
		try {
			if (null != x) {
				String[] imgarr = x.getClass().getMethod(getPictureMeth).invoke(x).toString().split("#");
				int i = 1;
				StringBuffer sb = new StringBuffer();
				for (String str : imgarr) {
					if (i == imgarr.length) {
					//	sb.append(CommonSystemConstants.SYS_IMGBASEURL + str);
					} else {
						//sb.append(CommonSystemConstants.SYS_IMGBASEURL + str + "#");
					}
					i++;
				}
				x.getClass().getDeclaredMethod(setPictureMeth, String.class).invoke(x, sb.toString());
				return x;
			}
			if (null != list && list.size() > 0) {
				for (T t : list) {

					String[] imgarr = t.getClass().getMethod(getPictureMeth).invoke(t).toString().split("#");
					int i = 1;
					StringBuffer sb = new StringBuffer();
					for (String str : imgarr) {
						if (i == imgarr.length) {
						//	sb.append(CommonSystemConstants.SYS_IMGBASEURL + str);
						} else {
							//s
							
							//sb.append(CommonSystemConstants.SYS_IMGBASEURL + str + "#");
						}
						i++;
					}
					t.getClass().getDeclaredMethod(setPictureMeth, String.class).invoke(t, sb.toString());

				}
				
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



}
