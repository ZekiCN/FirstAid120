package com.hc.utils;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Constant {

    public final static String PROJECT_CODE = "FirstAid";

    //权限码
    public final static String PROJECT_PER = "CPR_PC_";
    public final static String PERMISSION = "permission";

    //身份码
    public final static String PROJECT_IDE = "_PF_";
    public final static String IDENTITY = "identity";

    //单位
    public final static String UNIT = "unit";

    //身份码标识字段
    public final static String IDE_WORD = "perFlag";

    //字典值
    public final static String DICTDATA = "dictData";

    //字典项
    public final static String DICTCATALOG = "dictCatalog";

    //时间约束
    public final static String TIMECONSTRAINT = "timeConstraint";

    //服务平台标志码字典项
//    public final static String PRJCODE__PR_SP = "FirstAid";

	public final static String PAGE	= "10";			//分页条数配置路径
	public final static String TREE_TYPE_DICT_KEY	= "0001";			//组织树字典键dictKey B_dictCatalog-dictKey
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	
    // 定义分隔符"_"
    public final static String SPLIT = "_";
	
    //redis缓存，定义组织树数据key前缀
    public static String DICT_KEY_TO_DATA = "dictKeyToData";
  //redis缓存，定义公共模板树key前缀
    public static String TEMPLATE_TREE = "templateTree";
    
    //redis缓存数据过期时间 60*60*10
    public static int REDIS_INFO_LIFETIME = 36000;
    
	//参数区域相关代码，暂时写死实现
	public final static String AREA_CODE = "0000";//区域code
	public final static String PROVINCE = "广东省";//省份
	public final static String CITY = "惠州市";//城市
	public final static String COUNTY = "惠城区";//县城
	
}
