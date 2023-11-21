# sqltoy-idea-plugin

![Build](https://github.com/imyuyu/sqltoy-idea-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/23156-sqltoy-integration.svg)](https://plugins.jetbrains.com/plugin/23156-sqltoy-integration)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23156-sqltoy-integration.svg)](https://plugins.jetbrains.com/plugin/23156-sqltoy-integration)

本项目主要是由于原[sqltoy插件](https://github.com/threefish/sqltoy-idea-plugins)作者[threefish](https://github.com/threefish)没有持续更新，导致后续idea版本及sqltoy新版本无法兼容，所以创建次新工程;

## 介绍
<!-- Plugin description -->
sqltoy-orm is an orm framework that is more suitable for projects than Hibernate + MyBatis. It has the convenience of adding, deleting, modifying and loading objects, and also has more flexible and elegant custom SQL query functions than MyBatis.
<!-- Plugin description end -->
sqltoy-orm是比hibernate+myBatis更加贴合项目的orm框架，具有hibernate增删改和对象加载的便捷性同时也具有比myBatis更加灵活优雅的自定义sql查询功能.

### English
1. Support xml definition jump java call statement
2. java call jump xml definition
3. SQL statement highlighting
### 中文
1. 支持xml定义跳转java调用语句
2. java调用跳转xml定义
3. SQL语句高亮


## todo

- [X] 实现语法高亮
- [X] 实现java跳转xml定义
- [X] 实现xml跳转java调用
- [ ] 实现`double shift`搜索符号
- [ ] 实现sqlId的`find usages`
- [ ] 实现sql定义的中的`@`符号自动完成，如`@fast,@Loop`
- [ ] 实现java调用时的代码自动完成，自动提醒sqlId
- [ ] 未被调用的sqlId给予警告
- [ ] 提供新建`sql.xml`文件模板
- [ ] sql.xml文件图标改为特有标识

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
