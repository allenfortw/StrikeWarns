# StrikeWarns 插件 Wiki

## 插件简介

**StrikeWarns** 是一款用于 Minecraft 服务器的 Bukkit/Spigot 插件，旨在通过记录和管理玩家的违规次数（Strike）来维护服务器的秩序。当玩家的 Strike 数达到预设的阈值时，插件将自动执行相应的惩罚措施，如踢出或封禁玩家。此外，插件还支持与 Discord 机器人集成，方便服务器管理员通过 Discord 监控和管理玩家的行为。

### 主要功能

- **记录违规次数**：通过命令增加或设置玩家的 Strike 数。
- **自动执行惩罚**：根据配置文件中设定的 Strike 阈值，自动执行如踢出、封禁等命令。
- **命令自动补全**：提供友好的命令补全功能，提升管理员操作效率。
- **权限管理**：细分权限节点，确保不同权限的用户拥有不同的操作权限。
- **日志记录**：记录玩家达到 Strike 阈值时的执行情况，便于后续查阅。

## 指令说明

### 主指令

- `/strike`：查看自己的 Strike 数（需拥有 `strike.view` 权限）。

### 子指令

- `/strike help`：显示指令帮助信息。
- `/strike add <player> [count]`：为指定玩家增加 Strike 值，默认增加 1。
- `/strike set <player> <count>`：设置指定玩家的 Strike 值。
- `/strike reload`：重新加载插件的配置文件。

### 指令详细说明

| 指令                        | 描述                                     | 权限                        |
|-----------------------------|------------------------------------------|-----------------------------|
| `/strike`                   | 查看自己的 Strike 数                     | `strike.view`               |
| `/strike help`              | 显示所有可用指令的帮助信息               | `strikewarns.command.strike`|
| `/strike add <player> [count]` | 为指定玩家增加 Strike 值，默认增加 1       | `strikewarns.command.strike`|
| `/strike set <player> <count>` | 设置指定玩家的 Strike 值                  | `strikewarns.command.strike`|
| `/strike reload`            | 重新加载插件的配置文件                   | `strikewarns.command.strike`|

### 示例

- 查看自己的 Strike 数：
  ```
  /strike
  ```
  响应：
  ```
  [Strike] 您的Strike : 2
  ```

- 显示帮助信息：
  ```
  /strike help
  ```
  响应：
  ```
  ===== StrikeWarns 帮助 =====
  /strike help - 查看指令列表
  /strike add <player> <count> - 增加玩家的Strike值 (默认为1)
  /strike set <player> <count> - 设置玩家的Strike值
  /strike reload - 重新载入插件配置
  ==============================
  ```

- 为玩家增加 Strike：
  ```
  /strike add PlayerName 2
  ```
  响应：
  ```
  PlayerName 的Strike增加至: 3
  ```

- 设置玩家的 Strike 值：
  ```
  /strike set PlayerName 4
  ```
  响应：
  ```
  PlayerName 的Strike值設定為: 4
  ```

- 重新加载配置文件：
  ```
  /strike reload
  ```
  响应：
  ```
  StrikeWarns 配置已重新加载。
  ```

## 权限列表

| 权限节点                      | 描述                                     | 默认权限  |
|-------------------------------|------------------------------------------|-----------|
| `strikewarns.command.strike` | 允许访问所有 StrikeWarns 插件的指令功能 | `op`      |
| `strike.view`                 | 允许玩家查看自己的 Strike 数             | `true`    |

### 权限说明

- **`strikewarns.command.strike`**：
  - **描述**：允许用户使用所有与 StrikeWarns 插件相关的指令，包括 `help`、`add`、`set` 和 `reload`。
  - **默认权限**：仅 `op`（服务器管理员）拥有此权限。

- **`strike.view`**：
  - **描述**：允许玩家使用 `/strike` 指令查看自己的 Strike 数。
  - **默认权限**：所有玩家默认拥有此权限，可以在 `plugin.yml` 中进行调整。

### 权限配置示例

如果你使用权限管理插件（如 LuckPerms），可以通过以下命令设置权限：

- 给予管理员使用所有指令的权限：
  ```
  /lp user <username> permission set strikewarns.command.strike true
  ```

- 给予玩家查看自己 Strike 数的权限：
  ```
  /lp user <username> permission set strike.view true
  ```

## 安装方式

### 前置条件

1. **Minecraft 服务器**：确保你的服务器运行在支持 Bukkit 或 Spigot 插件的版本（推荐 Minecraft 1.16 及以上）。
2. **Java 环境**：建议使用 Java 8 或更高版本。
3. **构建工具**：建议使用 Maven 或 Gradle 来管理项目依赖（用于开发者）。

### 安装步骤

1. **下载插件**
   - 将编译好的 `StrikeWarns.jar` 文件下载到你的本地计算机。

2. **安装依赖**
   - **SQLite JDBC 驱动**：插件已内置 SQLite 支持，无需额外安装。
   - **Discord Bot（可选）**：如果需要与 Discord 集成，请确保在 `config.yml` 中正确配置 `discordBotToken`，并在 Discord 开发者门户创建并配置你的机器人。

3. **将插件添加到服务器**
   - 将 `StrikeWarns.jar` 文件复制到你的服务器目录下的 `plugins` 文件夹中。

4. **配置插件**
   - 启动服务器一次，插件将自动生成默认的 `config.yml` 和 `plugin.yml` 文件。
   - 停止服务器后，导航到 `plugins/StrikeWarns` 文件夹，编辑 `config.yml` 以根据你的需求进行配置。
   
   **示例 `config.yml`**：
   ```yaml
   discord_bot:
     discord_Bot_Token: 'YOUR_BOT_TOKEN'
   
   strike_count:
     1: 'kick %player% get strike 1'
     2: 'kick %player% get strike 2'
     3: 'kick %player% get strike 3'
     max: 'ban %player% get strike 4'
   
   strikeThreshold: 3
   strikeCommand: "ban %player%"
   ```

5. **设置权限**
   - 使用权限管理插件（如 LuckPerms）设置相应的权限节点，确保用户拥有适当的权限。

6. **启动服务器**
   - 启动你的 Minecraft 服务器，插件将自动加载并运行。
   - 你可以在服务器控制台中看到插件的启用信息：
     ```
     [StrikeWarns] StrikeWarns 插件已启用
     ```

7. **测试插件**
   - 登录服务器，使用 `/strike help` 指令查看帮助信息。
   - 尝试增加、设置 Strike 值，确保插件功能正常。

### 更新插件

- 定期检查插件的更新版本，并按照上述安装步骤替换旧版本的 `StrikeWarns.jar` 文件。
- 更新后，重新启动服务器以应用新版本的插件。

### 卸载插件

1. 停止服务器。
2. 从 `plugins` 文件夹中删除 `StrikeWarns.jar` 文件和相关配置文件夹（如 `StrikeWarns`）。
3. 启动服务器，插件将不再加载。

## 常见问题

**Q1: 插件无法启动或报错。**

- **解决方法**：
  - 确认服务器的 Minecraft 版本与插件兼容。
  - 检查 `config.yml` 是否配置正确，尤其是 `discordBotToken` 是否有效。
  - 确保服务器具有写入 `plugins/StrikeWarns` 文件夹的权限。

**Q2: 玩家执行 `/strike` 指令后无法查看自己的 Strike 数。**

- **解决方法**：
  - 确认玩家是否拥有 `strike.view` 权限。
  - 检查数据库是否正常连接，查看 `Database.db` 是否存在于 `plugins/StrikeWarns` 文件夹中。

**Q3: 自动执行的惩罚命令未生效。**

- **解决方法**：
  - 检查 `config.yml` 中 `strike_count` 的命令格式是否正确。
  - 确保服务器具有执行这些命令的权限（例如，封禁玩家需要服务器管理员权限）。
  - 查看服务器日志文件，确认是否有相关错误信息。

**Q4: 如何与 Discord 集成？**

- **解决方法**：
  - 在 `config.yml` 中正确配置 `discordBotToken`。
  - 确保你的 Discord 机器人具有必要的权限，并已邀请到你的 Discord 服务器中。
  - 检查插件日志，确认 Discord 机器人是否成功连接。

## 支持与反馈

如果你在使用 **StrikeWarns** 插件时遇到问题或有改进建议，请通过以下方式与我们联系：

- **GitHub 仓库**：[https://github.com/allenfortw/StrikeWarns](https://github.com/allenfortw/StrikeWarns)
- **Discord 服务器**：[加入我们的 Discord](https://discord.gg/5pjXXRc3Ut)
- **邮箱**：support@allenpixel.com

我们欢迎所有反馈，并致力于持续改进插件功能与稳定性。

---

*感谢您使用 StrikeWarns 插件！希望它能帮助您更好地管理 Minecraft 服务器，维护社区秩序。*
