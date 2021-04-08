# test
Android 课程源码

# 移动点餐系统开发日志

## 3月11号 

1、搭建用户界面Activity_main.xml
2、搭建用户注册界面Activity_register.xml

## 3月16日

1、编写实体类模型：菜品类Dish、菜单类Dishes、订单类Order、订单明细类OrderItem、用户类、购物车类ShoppingCart

## 3月18日

1、继续编写购物车类ShoppingCart

2、设计编写一个在APP运行期间能够使用用户购物车等对象的一个Application的类，共享数据，缓存数据，数据传递的作用

## 3月23日

#### 1.主界面的操作：

a.用户登录及注册。用户点击登录按钮，弹出登录及注册的对话框进行登录或注册操作。只有登录用户才可以进行“个人中心”、“点餐”、“外卖”、“我的订单”的操作，非登录用户系统会提示进行登录才能进行下一步操作。用户登录后，登录按钮切换成注销按钮

b、个人信息的查询和修改：登陆用户单机”个人中心“按钮，切换到“用户信息”页面进行个人信息的查看和修改

c、点餐：用户单击”点餐“按钮后，会弹出一个对话框，让用户输入餐桌号或包间号，输入完后切换到菜品页面

d、外卖：用户点击外卖按钮后会直接进入菜品页面进行点餐操作。

e、订单查询：登录用户点击“我的订单”就会进入到我的订单页面，进行订单的查询操作。

#### 2.在主界面给各个按钮添加事件监听（没有对imageBtnSetting绑定监听器，未对其点击进行处理）

## 3月25日

1.将用户注册界面跟一个Activity类的Java源文件关联，并注册该Activity，才能用

2.用ListView将菜品列表实现（四道菜的数据已经存到了myApplication里面的g_dishes.mDishes，适配器还未获取到菜品数据）

