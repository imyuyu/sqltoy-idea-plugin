<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# sqltoy-idea-plugin Changelog

## [Unreleased]

## [0.5.0] - 2024-06-14

### Added

- Support cache translation reference redirection
- Added language injections: 'SQLToy SQL ID' and 'SQLToy TRANSLATE ID'
- 支持`*-translate.xml`文件的缓存定义跳转至java代码`lightDao.getTranslateCache`、`new CacheMatchFilter().cacheName("dictKeyName")`、`new Translate("dictKeyName")`
- 支持`*-translate.xml`文件内部`Checker`属性的`cache`值跳转到缓存定义。`<sql-increment-checker cache="dictKeyName">`、`<sql-translate
  cache="dictKeyName">`
- 支持`*.sql.xml`文件`sql`定义中`cache`值跳转到缓存定义.`<sql id="sys_findStaffInfo"><translate cache="dictKeyName" columns="DICT_KEY" /></sql>`
- 以上所有xml中的`cache`属性,java中的`cacheName`均支持自动完成、自动提示
- `sqlId`、`cacheName`的引用均增加了下划线高亮.
- 增加了`SQLToy SQL ID`、`SQLToy TRANSLATE ID`两个语言注入，可以在任意字符串位置将某个字符串注入为`sqlId`或`cacheName`，注入后效果一致

### Changed

- Refactoring indexes while supporting SQLId and cache translation
- 重构了文件索引以同时支持`sqlId`和缓存翻译
- 优化部分代码匹配机制，提高性能及内存消耗

## [0.4.3] - 2024-05-28

### Added

- Inject the `generator` attribute of the `@Id` annotation into the Java class name
- The load parameter of the @OneToOne and @OneToMany annotations is injected as SQL, with support for syntax highlighting.
- `*-translate.xml` files support syntax highlighting.
- `@Id`注解的`generator`参数注入为java类名, 方便引用及跳转
- `@OneToOne`和`@OneToMany`注解的`load`参数注入为sql，支持语法高亮
- `*-translate.xml`文件支持语法注入
- `*-translate.xml`文件中的`datasource`属性、`service`属性直接语法注入为`spring bean name`方便跳转（有可能不生效，很诡异）
- `*-translate.xml` file use sqltoy icon

### Fixed

- 修复`2024.1`中语法注入报错的问题
- 优化语法注入逻辑
- 增强健壮性，修复在某些情况下插件报错的问题

## [0.4.2] - 2024-05-19

### Added

- Add SQL execution icon to the `*.sql.xml` 
- Syntax highlighting is supported when SQL statements are used directly in Java code
- `*.sql.xml`文件中增加一键执行sql语句(会打开database conole并自动填充sql)
- 在java代码中直接使用sql时支持语法高亮，如`lightDao.findPage(new Page(), "select * from staff_info")`;
- `new QueryExecutor("select * from staff_info")`的参数如果是普通sql，则支持语法高亮

## [0.4.1] - 2024-05-12

### Added

- Optimize quick document display content
- 优化快速文档展示效果，直接显示sql内容

## [0.4.0] - 2024-04-26

### Added

- added support for [SqlToy-Plus](https://gitee.com/gzghde/sqltoy-plus);
- sqltoy sql xml inspection
- sqltoy framework detector
- never use SQL checks
- 支持单sql.xml文件的语法检查，如id必填，id重复(仅当前xml文件)等
- 支持自动探测sqltoy框架(暂时太大用处)
- 支持sqlid未使用检测，当前实现比较简单，未来再扩充
- 支持sql.xml中针对id查找使用

### Fixed

- Fix the issue that caused code prompts to become invalid
- 修复影响代码提示的问题

## [0.3.0] - 2024-04-07

### Added

- added support for IntelliJ IDEA 2024.1.
- 实现java调用时的代码自动完成，自动提醒sqlId
- 实现java调用中时，快速文档展示

## [0.2.0]

### Added

- Provide templates for creating new `sql.xml` and sqltoy `translate.xml` files
- Support using `double shift` to search for `sqlId`
- 支持快捷新建`*.sql.xml`和缓存翻译`translate.xml`
- 支持使用 `double shift` 搜索 `sqlId`

## [0.1.0]

### Added

- [SQL statement highlighting](https://github.com/imyuyu/sqltoy-idea-plugin/issues/5)
- [Support xml definition jump java call statement](https://github.com/imyuyu/sqltoy-idea-plugin/issues/3)
- [java call jump xml definition](https://github.com/imyuyu/sqltoy-idea-plugin/issues/4)
- `sql.xml` file use sqltoy icon

[Unreleased]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.5.0...HEAD
[0.5.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.4.3...v0.5.0
[0.4.3]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.4.2...v0.4.3
[0.4.2]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.4.1...v0.4.2
[0.4.1]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.4.0...v0.4.1
[0.4.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/imyuyu/sqltoy-idea-plugin/commits/v0.1.0
