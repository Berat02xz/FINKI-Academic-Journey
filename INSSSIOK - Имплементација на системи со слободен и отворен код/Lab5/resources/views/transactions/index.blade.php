<!-- resources/views/transactions/index.blade.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Transactions</h1>
    <a href="{{ route('transactions.create') }}" class="btn btn-primary mb-3">Add New Transaction</a>

    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Employee Name</th>
            <th>Sender Name</th>
            <th>Receiver Name</th>
            <th>Amount</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        @foreach($transactions as $transaction)
            <tr>
                <td>{{ $transaction->id }}</td>
                <td>{{ $transaction->employee_name }}</td>
                <td>{{ $transaction->sender_name }}</td>
                <td>{{ $transaction->receiver_name }}</td>
                <td>${{ number_format($transaction->amount, 2) }}</td>
                <td>
                    <a href="{{ route('transactions.edit', $transaction->id) }}" class="btn btn-warning btn-sm">Edit</a>
                    <form action="{{ route('transactions.destroy', $transaction->id) }}" method="POST" style="display: inline;">
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</button>
                    </form>
                </td>
            </tr>
        @endforeach
        </tbody>
    </table>

    <div>
        <strong>Total Transactions:</strong> {{ $transactions->count() }}<br>
        <strong>Total Amount:</strong> ${{ number_format($transactions->sum('amount'), 2) }}
    </div>
</div>
</body>
</html>
