using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace BookingApplication.Migrations
{
    /// <inheritdoc />
    public partial class final : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BookReservation_Reservations_ReservationId",
                table: "BookReservation");

            migrationBuilder.AlterColumn<Guid>(
                name: "ReservationId",
                table: "BookReservation",
                type: "uniqueidentifier",
                nullable: true,
                oldClrType: typeof(Guid),
                oldType: "uniqueidentifier");

            migrationBuilder.AddForeignKey(
                name: "FK_BookReservation_Reservations_ReservationId",
                table: "BookReservation",
                column: "ReservationId",
                principalTable: "Reservations",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BookReservation_Reservations_ReservationId",
                table: "BookReservation");

            migrationBuilder.AlterColumn<Guid>(
                name: "ReservationId",
                table: "BookReservation",
                type: "uniqueidentifier",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"),
                oldClrType: typeof(Guid),
                oldType: "uniqueidentifier",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_BookReservation_Reservations_ReservationId",
                table: "BookReservation",
                column: "ReservationId",
                principalTable: "Reservations",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
