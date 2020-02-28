package com.example.demo.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.LoginLog;
import com.example.demo.model.ReturnResult;
import com.example.demo.service.impl.LoginLogServiceImpl;

/**
 *
    *控制器
 * @author admin
 * @since 2019-12-19
 */
@RestController
@RequestMapping("/login-log")
public class LoginLogController extends BaseController {
	
	@Autowired
    private LoginLogServiceImpl loginLogService;
    
    
   /**
	 *   查询数据
	 * @param request
	 * @return
	 */
	@GetMapping("/query")
	public ReturnResult loginLoglist(HttpServletRequest request,@RequestParam Map<String,Object> paramMap) {	
		try {
			if(!StringUtils.isEmpty(paramMap.get("id"))){//通过id查询一条
				LoginLog loginLog = loginLogService.getById(Integer.valueOf(paramMap.get("id").toString()));
				return new ReturnResult(ReturnResult.SUCCESS,null,loginLog);
			}
			//分页器
			Page<LoginLog> page=null;
			if(paramMap.get("pageNum")!=null || paramMap.get("pageSize")!=null){
				try {
					//分页器
					page = new Page<>((Long.valueOf(String.valueOf(paramMap.get("pageNum")))),
						Long.valueOf(String.valueOf(paramMap.get("pageSize"))));
				} catch (Exception e) {
					return new ReturnResult(ReturnResult.FAILURE,"pageNum or pageSize 参数有误!",null);
				}
			}
			//条件构造器
			QueryWrapper<LoginLog> queryWrapper = new QueryWrapper<>();
			/**
			*	do something
			*	在查询条件构造器中添加条件
			*/		
			//后续进行条件构造不需要这俩参数,故移除
			paramMap.remove("pageNum");
			paramMap.remove("pageSize");
			//字段模糊查询条件添加
			Set<String> keySet = paramMap.keySet();
			for (String col : keySet) {
				queryWrapper.like(col, paramMap.get(col));
			}
			Map<String, Object> data=new HashMap<>();
			if(page==null){//条件查询
				List<LoginLog> list = loginLogService.list(queryWrapper);
				data.put("total", list.size());
				data.put("records", list);
			}else{	//分页条件查询
				IPage<LoginLog> iPage = loginLogService.page(page, queryWrapper);
				data.put("totalSize", iPage.getTotal());
				data.put("pageSize", iPage.getSize());
				data.put("pageNum", iPage.getCurrent());
				data.put("totalPage", iPage.getPages());
				data.put("records", iPage.getRecords());
			}
			return new ReturnResult(ReturnResult.SUCCESS,null,data);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResult(ReturnResult.FAILURE,ExceptionUtils.getMessage(e),null);
		}	
	}
	
    /**
    *保存数据
    * @param loginLog 实体对象
	* @return
    */
    @PostMapping("/save")
    public ReturnResult Save(LoginLog loginLog) {
    	ReturnResult returnResult=new ReturnResult();
        try {
        	/**
        	*新增或修改，当对象有id时为修改，没有id则为新增
        	*/
            boolean res=loginLogService.saveOrUpdate(loginLog);
        	returnResult.setCode(ReturnResult.SUCCESS);
            if(res){
				returnResult.setMessage("操作成功！");
            }else{
				returnResult.setMessage("操作成功！数据未变化！");
            }
        } catch (Exception e) {
        	returnResult.setCode(ReturnResult.FAILURE);
        	returnResult.setMessage("操作失败！");
            e.printStackTrace();
        }
        return returnResult;
    }
     /**
    *根据id删除数据
    * @param id  
	* @return
    */
	@PostMapping("/delete")
	public ReturnResult delete(HttpServletRequest request,@NotBlank(message = "{required}") int id){
		ReturnResult returnResult=new ReturnResult();
		try {
			loginLogService.removeById(id);
			returnResult.setCode(ReturnResult.SUCCESS);
			returnResult.setMessage("删除成功");
		} catch (Exception e) {
			returnResult.setCode(ReturnResult.FAILURE);
			returnResult.setMessage("删除失败");
			e.printStackTrace();
		}
		return returnResult;
	} 
	/**
	 * 根据id批量删除数据
	 * 
	 * @param ids
	 * @return
	 */
	@PostMapping("/batchDelete")
	public ReturnResult delete(String[] ids) {
		ReturnResult returnResult = new ReturnResult();
		try {
			List<String> asList = Arrays.asList(ids);
			loginLogService.removeByIds(asList);
			returnResult.setCode(ReturnResult.SUCCESS);
			returnResult.setMessage("批量删除成功");
		} catch (Exception e) {
			returnResult.setCode(ReturnResult.FAILURE);
			returnResult.setMessage("批量删除失败");
			e.printStackTrace();
		}
		return returnResult;
	}

}

