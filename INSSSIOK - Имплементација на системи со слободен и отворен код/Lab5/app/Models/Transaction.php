<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Transaction extends Model
{
    use HasFactory;

    protected $fillable = [
        'employee_name',
        'sender_name',
        'receiver_name',
        'sender_account',
        'receiver_account',
        'amount',
    ];
}
