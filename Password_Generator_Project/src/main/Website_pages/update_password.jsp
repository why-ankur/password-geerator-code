<!DOCTYPE html>
<html>
<head>
    <title>Update Password</title>
</head>
<body>
    <h2>Update User Password</h2>
    <form method="post" action="UserServlet">
        <input type="hidden" name="action" value="updatePassword">
        Phone: <input type="text" name="phone" required><br>
        Platform: <input type="text" name="platform" required><br>
        New Password: <input type="text" name="newPassword" required><br>
        <button type="submit">Update Password</button>
    </form>
</body>
</html>
