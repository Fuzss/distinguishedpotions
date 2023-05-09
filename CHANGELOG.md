# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog].

## [v5.0.0-1.19.3] - 2023-03-25
- Ported to Minecraft 1.19.3
### Added
- Added new potion colors from Minecraft 1.19.4+, they are fully customizable in the config
### Changed
- Distinguished Potions can now also be installed server-side to make new potion colors apply to the particles of splash and lingering potions
- Strong and long potions now feature unique bottle shapes in addition to the cork having a new color
- Strong and long potions are no longer hardcoded and can be set in the config in an effort to support other mods that do not follow vanilla potion naming conventions
- The dotted potion bar is now disabled by default
### Removed
- Removed the option to exclude tipped arrows from having any changes applied to them

[Keep a Changelog]: https://keepachangelog.com/en/1.0.0/
