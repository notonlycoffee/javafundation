package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;


/**
 * 
 *Title:
 *Description: 
 *	这个类跟Employee2类一起，总结的内容是StaticTest类可以调用Employee2类中所有的公共方法 ，包括main方法也是。
 *	这里的Employee2类中因为有main方法，也是可以自己运行的，同时也可以给别人调用。
 * @author jc
 * 2016年6月10日下午2:52:39
 */
public class StaticTest
{
   public static void main(String[] args)
   {
      // fill the staff array with three Employee objects
      Employee2[] staff = new Employee2[3];

      staff[0] = new Employee2("Tom", 40000);
      staff[1] = new Employee2("Dick", 60000);
      staff[2] = new Employee2("Harry", 65000);

      // print out information about all Employee objects
      for (Employee2 e : staff)
      {
         e.setId();
         System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary="
               + e.getSalary());
      }

      int n = Employee2.getNextId(); // calls static method
      System.out.println("Next available id=" + n);
      
      Employee2.main(args);//
      
      
   }
}