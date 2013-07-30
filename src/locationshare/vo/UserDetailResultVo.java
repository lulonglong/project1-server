package locationshare.vo;

import java.text.MessageFormat;

import locationshare.base.vo.BaseResultVO;
import locationshare.hibernate.TbUserDetail;

public class UserDetailResultVo extends BaseResultVO {

	public static final String successTemplate = "'{'\"code\":\"0\",\"nickname\":\"{0}\",\"email\":\"{1}\",\"phonenumber\":\"{2}\",\"sex\":\"{3}\",\"age\":\"{4}\",\"school\":\"{5}\"'}'";

	public String toSuccessJsonResult(TbUserDetail userDetail) {
		return MessageFormat.format(successTemplate, userDetail.getNickname(),
				userDetail.getEmail(), userDetail.getTelephone(),
				userDetail.getSex(), userDetail.getAge(),
				userDetail.getSchool());
	}
}
