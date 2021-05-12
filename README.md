## 一、项目设计的知识点

- activity的跳转
- fragment和viewpager的结合
- 根据所学的控件设计页面
- 广播和服务
- sqlite数据存储
- 背景音乐的播放
- Json数据解析

## 二、项目设计

设计一个音乐播放类的app，可以进行歌曲的播放，以及暂停等功能

## 三、详细设计与实现

主页面

主页面采用的是TabLayout+ViewPager+Fragment组合成一个顶部导航栏，而且可以进行手动翻页或者点击切换页面等操作

MusifFragment

里边有一个listview用于展示音乐的数据，且每个item可以点击，点击后将进入到音乐详情页面

MusicActivity

音乐详情页面，在这里会对音乐进行一个播放，

这里将会出现下列几种情况



```sqlite
create table Music(
    id integer primary key autoincrement,
    name text,
    author text,
    cover integer,
    resrouce integer,
    type integer
)
```

