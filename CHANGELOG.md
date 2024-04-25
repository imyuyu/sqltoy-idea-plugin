<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# sqltoy-idea-plugin Changelog

## [Unreleased]

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

### 

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

[Unreleased]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.3.0...HEAD
[0.3.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/imyuyu/sqltoy-idea-plugin/compare/v0.1.0...v0.2.0
[0.1.0]: https://github.com/imyuyu/sqltoy-idea-plugin/commits/v0.1.0
