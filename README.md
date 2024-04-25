# sqltoy-idea-plugin

![Build](https://github.com/imyuyu/sqltoy-idea-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/23156-sqltoy-integration.svg)](https://plugins.jetbrains.com/plugin/23156-sqltoy-integration)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23156-sqltoy-integration.svg)](https://plugins.jetbrains.com/plugin/23156-sqltoy-integration)

本项目主要是由于原[sqltoy插件](https://github.com/threefish/sqltoy-idea-plugins)作者[threefish](https://github.com/threefish)没有持续更新，导致后续idea版本及sqltoy新版本无法兼容，所以创建此新工程;

## 介绍

### English

<!-- Plugin description -->
sqltoy-orm is an orm framework that is more suitable for projects than Hibernate + MyBatis. It has the convenience of adding, deleting, modifying and loading objects, and also has more flexible and elegant custom SQL query functions than MyBatis.

SQLToy Plugin Features:
- Support xml definition jump java call statement 
- java call jump xml definition
- SQL statement highlighting
<!-- Plugin description end -->

### 中文

sqltoy-orm是比hibernate+myBatis更加贴合项目的orm框架，具有hibernate增删改和对象加载的便捷性同时也具有比myBatis更加灵活优雅的自定义sql查询功能.

插件功能：

- 支持xml定义跳转java调用语句 
- java调用跳转xml定义
- SQL语句高亮


## todo

- [X] 实现语法高亮
- [X] 实现java跳转xml定义
- [X] 实现xml跳转java调用
- [X] sql.xml文件图标改为特有标识
- [X] 提供新建`sql.xml`、`sqltoy-translate.xml`文件模板
- [X] 实现`double shift`搜索符号
- [X] 实现xml中sqlId的`find usages`
- [X] 实现java调用时的代码自动完成，自动提醒sqlId
- [X] 实现java调用中时，快速文档展示
- [X] 未被调用的sqlId给予警告
- [X] sqlId重复报错
- [ ] 实现sql定义的中的`@`符号自动完成，如`@fast,@Loop`
- [ ] 缓存翻译跳转
- [ ] 缓存翻译自动完成


## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "sqltoy-idea-plugin"</kbd> >
  <kbd>Install</kbd>
  
- Manually:

  Download the [latest release](https://github.com/imyuyu/sqltoy-idea-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
本工程参考了以下项目，感谢
- [sqltoy-idea-plugins]，感谢[threefish](https://github.com/threefish)的代码
- [MybatisX]


[sqltoy-idea-plugins]: https://github.com/threefish/sqltoy-idea-plugins
[MybatisX]: https://github.com/baomidou/MybatisX
