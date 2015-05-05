Rails 部署中的基本命令及技巧
----

### 基本的产生命令

- 禁止安装生产环境所需的gem（适合开发环境）

    `bundle install --without production `

- 创建一个rails项目，不生成默认的Test::Unit测试框架

    `rails new app --skip-test-unit`

- 设置让rails适用RSpec而不使用Test::Unit
    
    `rails generate rspec:install`

- 禁止生成RSpec测试代码

    `rails generate controller Pages home --no-test-framework`

### 撤销操作

- controller的产生与撤销
    
    `rails generate controller FooBars baz quux  ##产生`

    `rails destroy  controller FooBars baz quux  ##撤销`

- model的产生与撤销

   `rails generate model Foo bar:String ##产生`
   
   `rails destroy  model Foo            ##撤销`

- 数据库的产生与撤销
    
   `rake db:migrate   ##产生`
    
   `rake db:rollback  #撤销`

###在rails console中使用模型校验

首先在model中加入校验的代码

`validates :name, presence: true`

然后在控制台可以查看校验的结果

```
$ rails console --sandbox
>> user = User.new(name: "", email: "mhartl@example.com")
>> user.save
=> false
>> user.valid?
=> false
```
查看失败信息

```
>> user.errors.full_messages
=> ["Name can't be blank"]
```
###在页面上加入debug信息

```
<%= debug(params) if Rails.env.development? %>
```
###编程技巧

- 重置session

`reset_session`
