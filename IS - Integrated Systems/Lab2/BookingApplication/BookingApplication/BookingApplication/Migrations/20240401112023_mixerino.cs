using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace BookingApplication.Migrations
{
    /// <inheritdoc />
    public partial class mixerino : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BookReservation_BookingList_BookingListId",
                table: "BookReservation");

            migrationBuilder.AlterColumn<Guid>(
                name: "BookingListId",
                table: "BookReservation",
                type: "uniqueidentifier",
                nullable: true,
                oldClrType: typeof(Guid),
                oldType: "uniqueidentifier");

            migrationBuilder.AddColumn<string>(
                name: "UserId",
                table: "BookReservation",
                type: "nvarchar(450)",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_BookReservation_UserId",
                table: "BookReservation",
                column: "UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_BookReservation_AspNetUsers_UserId",
                table: "BookReservation",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_BookReservation_BookingList_BookingListId",
                table: "BookReservation",
                column: "BookingListId",
                principalTable: "BookingList",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_BookReservation_AspNetUsers_UserId",
                table: "BookReservation");

            migrationBuilder.DropForeignKey(
                name: "FK_BookReservation_BookingList_BookingListId",
                table: "BookReservation");

            migrationBuilder.DropIndex(
                name: "IX_BookReservation_UserId",
                table: "BookReservation");

            migrationBuilder.DropColumn(
                name: "UserId",
                table: "BookReservation");

            migrationBuilder.AlterColumn<Guid>(
                name: "BookingListId",
                table: "BookReservation",
                type: "uniqueidentifier",
                nullable: false,
                defaultValue: new Guid("00000000-0000-0000-0000-000000000000"),
                oldClrType: typeof(Guid),
                oldType: "uniqueidentifier",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_BookReservation_BookingList_BookingListId",
                table: "BookReservation",
                column: "BookingListId",
                principalTable: "BookingList",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
