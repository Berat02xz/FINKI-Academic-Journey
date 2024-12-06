<!-- resources/views/transactions/create.blade.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Add New Transaction</h1>

    <!-- Form to create a new transaction -->
    <form action="{{ route('transactions.store') }}" method="POST">
        @csrf
        <div class="form-group">
            <label for="employee_name">Employee Name</label>
            <input type="text" class="form-control" id="employee_name" name="employee_name" required>
        </div>
        <div class="form-group">
            <label for="sender_name">Sender Name</label>
            <input type="text" class="form-control" id="sender_name" name="sender_name" required>
        </div>
        <div class="form-group">
            <label for="receiver_name">Receiver Name</label>
            <input type="text" class="form-control" id="receiver_name" name="receiver_name" required>
        </div>
        <div class="form-group">
            <label for="sender_account">Sender Account</label>
            <input type="text" class="form-control" id="sender_account" name="sender_account" required>
        </div>
        <div class="form-group">
            <label for="receiver_account">Receiver Account</label>
            <input type="text" class="form-control" id="receiver_account" name="receiver_account" required>
        </div>
        <div class="form-group">
            <label for="amount">Amount</label>
            <input type="number" class="form-control" id="amount" name="amount" required>
        </div>

        <button type="submit" class="btn btn-success">Create Transaction</button>
    </form>
</div>
</body>
</html>
