git conifg --global user.name "Melody12ab"
git config --global user.email "357170967@qq.com"

git init 
//必须先add  才能commit
git add <file>
git commit -m "changes message"

git status
git diff <file>

//查看日志
git log (--pretty=oneline)(会出现 commit id)

//回退到以前的版本
#HEAD代表当前版本  上一个版本是HEAD^ 上上个是HEAD^^   前100个就是HEAD~100
git reset --hard HEAD^
cat <file>
#再次回到未来就用
git reflog (找到commit_id)

git 管理的是修改  不管理文件


//撤销修改
#将<file>在工作区的修改全部撤销，回到最近一次的状态
git checkout -- <file>

//删除文件
rm text.txt
#还要删除版本库中的（可撤销）
git rm text.txt

//创建SSH
ssh-keygen -t rsa -C "youremail@example.com"

//远程功能
#查看远程库信息
git remote /git remote -v
git remote add origin git@github.com:michaelliao/learngit.git
#第一次需要加-u 还会推送branch
git push -u origin master

//远程clone到本地
git clone git@github.com:michaelliao/learngit.git

//创建并切换到分支
#查看当前分支
git branch

git branch dev  #创建
git checkout dev  #切换
(git checkout -b dev)

#合并分支(合并指定分支到当前分支)
git merge dev 
#删除分支 
git branch -d dev


//带参数的git log查看分支的合并情况
git log --graph --pretty=oneline --abbrev-commit


//创建临时工作现场
git stash
#查看
git stash list
#恢复
git stash apply(不删除stash)
git stash pop(删除stash)


//开发一个新功能或修复bug最好建议新建一个分支
#没有合并修改的分支，可以通过以下办法强制删除
git branch -D <name>


//多人协作的工作模式通常是这样：

#推送自己的修改；
git push origin branch-name

#如果推送失败，则因为远程分支比你的本地更新,试图合并；
git pull

#如果合并有冲突，则解决冲突，并在本地提交；
git push origin branch-name

如果git pull提示“no tracking information”，
则说明本地分支和远程分支的链接关系没有创建，用命令
git branch --set-upstream branch-name origin/branch-name。


//打标签  先切换到要打标签的分支上
git tag <name>
#查看所有标签
git tag

//给以前的代码打标签
git tag <tagname> commitId
#查看标签信息
git show <tagname>
git tag -a <tagname> -m "commit_message"
#私钥标签
git tag -s <tagname> -m "commit message"
#删除标签
git tag -d <tagname>
#推送
git push origin <tagname>
#一次性推送全部没推送的tag
git push origin --tags
#删除tag
git tag -d <tagname>
(git push origin :refs/tags/<tagname>)


//git显示不同的颜色
git config --global color.ui true

//忽略特殊文件
.gitignore
https://github.com/github/gitignore

//配置别名
git config --global alias.st status
git config --global alias.co checkout
git config --global alias.ci commit
git config --global alias.br branch
git config --global alias.unstage 'reset HEAD'
git config --global alias.last 'log -1'
git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset 
-%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
#配置文件
.git/config(全局配置)
.gitconfig(当前用户目录下)



//搭建git服务器
1.sudo apt-get install git
2.sudo adduser git
3.收集所有需要登录的用户的公钥，就是他们自己的id_rsa.pub文件，
把所有公钥导入到/home/git/.ssh/authorized_keys文件里，一行一个。
4.先选定一个目录作为Git仓库，假定是/srv/sample.git，在/srv目录下输入命令：
sudo git init --bare sample.git
5.sudo chown -R git:git sample.git
6.编辑/etc/passwd 禁用shell登录
git:x:1001:1001:,,,:/home/git:/bin/bash  改为
git:x:1001:1001:,,,:/home/git:/usr/bin/git-shell
7.git clone git@server:/srv/sample.git