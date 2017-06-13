# AudioPlayer

音乐播放器的后台（使用CommonsFileUpLoad插件实现文件的上传下载），团队协作开发，播放器前端传输门 https://github.com/linlinNB/Vue_music.git


1.用户管理，用户头像使用二进制文件保存如数据库中。<br>

2.音乐文件保存到服务器指定文件夹中，将文件地址保存如数据库中。<br>

3.文件上传下载缓冲，还没有考虑好。<br>

4.文件上传下载文件大小的限制，没有设置。<br>

5.用户头像保存如数据库之前没有做压缩处理，真实保存缩略图即可，用户头像上传前应该在前端做处理（切割为尺寸大小合适的图片）<br>

6.准备项目上传。<br>


参考博客：

1.【http://blog.csdn.net/zhoukun1008/article/details/52471776

2.【http://www.cnblogs.com/cenyu/p/6208144.html


Knowledge:

1.servlet中的几个方法：
	
	构造方法：一定会执行，并且第一个执行，servlet需要完成的功能可以过载到其中，这样和get或post没有了关系，但是不能这样做。
	
	doGet(request,response):当请求为get方式时，执行这个方法，在这个方法中实现servlet的功能。
	
	doPost(request,response):当请求为post方式时。执行这个方法，在这个方法中可以调用doGet方法。
	
	通常的一些做法：为了偷懒，不区分get或post请求方式，通常在doPost中直接调用doGet方法，将request和response当参数转发，最后在doGet方法中实现功能.
	
	其他的HTTP请求方式：PUT,DELETE，不常用的一些方式。注意区分GET和POST请求方式的不同。
	
	参考知乎回答，tomcat中doGet和doPost的不同执行，以及使用注意。get能被爬虫请求，所有get中不应该有对服务器有副作用(增删改)的操作，防止攻击。
	
	日志(access log)中会保存get和post的访问记录，如果get中有用户数据，会泄漏用户的信息。post(日志中只保存访问的servler不保存数据)方式做修改不会泄漏。
	
	**【https://www.zhihu.com/question/20144588  
	
2.复习get与post对比
	GET 
	请求会有 cacheGET 
	请求会保留在浏览历史中
	GET 请求可以保存到书签
	GET 请求不应用于处理敏感数据
	GET 请求有长度限制
	GET 请求应该只用于获取数据
	POST 
	不会有cache
	POST 请求不会出现在浏览器的浏览历史中
	POST 请求不能保存到书签
	POST 也是有长度限制的(不同的Web Server可能实现不同)
