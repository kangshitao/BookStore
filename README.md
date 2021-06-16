# BookStore
基于JSP+Sevlet+MySQL的简易书城项目

参考自[尚硅谷JavaWeb基础教程](https://www.bilibili.com/video/BV1Y7411K7zz)

主要功能：
* 用户创建，登陆注册校验
* 图书管理，增删查改图书
* 购物车功能，订单生成与结算
* 订单状态（待完善）


各目录说明：
* main：项目源代码
  * java：Servlet、DAO、Bean等
    * bean：JavaBean文件
    * dao：DAO类，用于操作数据库，共service层调用
    * filter：过滤器，用于权限管理
    * service：service层，调用DAO层，提供对各个对象的操作方法，供servlet层调用。
    * utils：工具类，包括JDBC连接池和JavaBean对象注入工具类
    * web：Servlet类，处理页面的请求，调用service层方法完成功能
  * resources：JDBC配置文件
  * webapp：JSP页面以及静态文件
    * pages：购物车页面、主页、公共元素、错误页面、管理页面、订单页面、用户登陆和注册页面
    * static：css样式文件、img文件、jquery库
    * WEB-INF：配置文件和jar包
    * index.jsp：主页(用于跳转)
* test：测试代码


MySQL表:
* `t_book`
```mysql
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `author` varchar(100) NOT NULL,
  `sales` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `img_path` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

* `t_user`
```mysql
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

* `t_order`
```mysql
CREATE TABLE `t_order` (
  `order_id` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

* `t_order_item`
```mysql
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `total_price` decimal(11,2) DEFAULT NULL,
  `order_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

```
