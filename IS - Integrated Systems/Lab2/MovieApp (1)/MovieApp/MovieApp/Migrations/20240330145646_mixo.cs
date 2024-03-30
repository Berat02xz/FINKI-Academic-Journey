using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MovieApp.Migrations
{
    /// <inheritdoc />
    public partial class mixo : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_EShopApplicationUserId",
                table: "TicketInOrders");

            migrationBuilder.RenameColumn(
                name: "EShopApplicationUserId",
                table: "TicketInOrders",
                newName: "UserId");

            migrationBuilder.RenameIndex(
                name: "IX_TicketInOrders_EShopApplicationUserId",
                table: "TicketInOrders",
                newName: "IX_TicketInOrders_UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_UserId",
                table: "TicketInOrders",
                column: "UserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_UserId",
                table: "TicketInOrders");

            migrationBuilder.RenameColumn(
                name: "UserId",
                table: "TicketInOrders",
                newName: "EShopApplicationUserId");

            migrationBuilder.RenameIndex(
                name: "IX_TicketInOrders_UserId",
                table: "TicketInOrders",
                newName: "IX_TicketInOrders_EShopApplicationUserId");

            migrationBuilder.AddForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_EShopApplicationUserId",
                table: "TicketInOrders",
                column: "EShopApplicationUserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id");
        }
    }
}
