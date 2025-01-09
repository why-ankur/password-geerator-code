<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h2>Add New User</h2>
    <form method="post" action="UserServlet">
        <input type="hidden" name="action" value="addUser">
        Phone: <input type="text" name="phone" required><br>
        DOB: <input type="date" name="dob" required><br>
        Name: <input type="text" name="name" required><br>
        Platform: <input type="text" name="platform" required><br>
        <button type="submit">Add User</button>
    </form>
</body>
</html>
