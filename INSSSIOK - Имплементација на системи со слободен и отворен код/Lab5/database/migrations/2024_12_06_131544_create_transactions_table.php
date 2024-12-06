<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('transactions', function (Blueprint $table) {
            $table->id();
            $table->string('employee_name');  // Name and surname of the employee
            $table->string('sender_name');    // Name and surname of the sender
            $table->string('receiver_name');  // Name and surname of the receiver
            $table->string('sender_account');  // Sender's transaction account
            $table->string('receiver_account'); // Receiver's transaction account
            $table->decimal('amount', 15, 2); // Amount sent
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('transactions');
    }
};
