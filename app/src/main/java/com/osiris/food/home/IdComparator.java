package com.osiris.food.home;

import com.osiris.food.model.StudyCourse;

import java.util.Comparator;

public class IdComparator implements Comparator {
	public int compare(Object object1, Object object2) {// 实现接口中的方法
		StudyCourse p1 = (StudyCourse) object1; // 强制转换
		StudyCourse p2 = (StudyCourse) object2;
		return new Integer(p1.getId()).compareTo(new Integer(p2.getId()));
	}
}
