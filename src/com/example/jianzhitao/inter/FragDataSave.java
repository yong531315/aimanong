package com.example.jianzhitao.inter;

import java.util.List;

import com.example.jianzhitao.bean.article;

/**
 * @author ysnow
 *用于在fragment被destroy之后的数据恢复
 */
public interface FragDataSave {
		
		public void onFragDestroy(int which,List<article> list);
		
		public List<article>  onFragCreate(int which);
			
}
