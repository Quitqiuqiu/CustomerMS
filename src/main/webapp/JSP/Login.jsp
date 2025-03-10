<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('your-background-image.jpg'); /* 替换为你的图片路径 */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            min-height: 100vh;
        }
        .login-box {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 400px;
            width: 100%;
        }
        .center-screen {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
    </style>
</head>
<body>
    <div class="center-screen">
        <div class="login-box">
            <h2 class="text-center mb-4">系统登录</h2>
            <form action="login" method="post">
                <!-- 身份选择 -->
                <div class="mb-3">
                    <label class="form-label">登录身份</label>
                    <div class="d-flex gap-3">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" 
                                   id="customer" value="customer" checked>
                            <label class="form-check-label" for="customer">客户</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" 
                                   id="admin" value="admin">
                            <label class="form-check-label" for="admin">管理员</label>
                        </div>
                    </div>
                </div>

                <!-- 用户名 -->
                <div class="mb-3">
                    <label for="username" class="form-label">用户名</label>
                    <input type="text" class="form-control" 
                           id="username" name="username" required>
                </div>

                <!-- 密码 -->
                <div class="mb-3">
                    <label for="password" class="form-label">密码</label>
                    <input type="password" class="form-control" 
                           id="password" name="password" required>
                </div>

                <!-- 登录按钮 -->
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">登录</button>
                </div>
            </form>

            <!-- 注册链接 -->
            <div class="text-center mt-3">
                <p class="mb-0">没有账号？<a href="/CustomerMS//HTML/Register.html" class="text-decoration-none">立即注册</a></p>
            </div>
        </div>
    </div>
</body>
</html>