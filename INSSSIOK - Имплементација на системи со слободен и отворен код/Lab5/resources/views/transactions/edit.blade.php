<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container">
        <h1>Edit Transaction</h1>

        <!-- Form to edit an existing transaction -->
        <form action="{{ route('transactions.update', $transaction->id) }}" method="POST">
            @csrf
            @method('PUT')

            <!-- Employee Name -->
            <div class="form-group">
                <label for="employee_name">Employee Name</label>
                <input type="text" class="form-control" id="employee_name" name="employee_name" value="{{ old('employee_name', $transaction->employee_name) }}" required>
            </div>

            <!-- Sender Name -->
            <div class="form-group">
                <label for="sender_name">Sender Name</label>
                <input type="text" class="form-control" id="sender_name" name="sender_name" value="{{ old('sender_name', $transaction->sender_name) }}" required>
            </div>

            <!-- Receiver Name -->
            <div class="form-group">
                <label for="receiver_name">Receiver Name</label>
                <input type="text" class="form-control" id="receiver_name" name="receiver_name" value="{{ old('receiver_name', $transaction->receiver_name) }}" required>
            </div>

            <!-- Sender Account -->
            <div class="form-group">
                <label for="sender_account">Sender Account</label>
                <input type="text" class="form-control" id="sender_account" name="sender_account" value="{{ old('sender_account', $transaction->sender_account) }}" required>
            </div>

            <!-- Receiver Account -->
            <div class="form-group">
                <label for="receiver_account">Receiver Account</label>
                <input type="text" class="form-control" id="receiver_account" name="receiver_account" value="{{ old('receiver_account', $transaction->receiver_account) }}" required>
            </div>

            <!-- Amount -->
            <div class="form-group">
                <label for="amount">Amount</label>
                <input type="number" class="form-control" id="amount" name="amount" value="{{ old('amount', $transaction->amount) }}" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-warning">Update Transaction</button>
        </form>
    </div>

