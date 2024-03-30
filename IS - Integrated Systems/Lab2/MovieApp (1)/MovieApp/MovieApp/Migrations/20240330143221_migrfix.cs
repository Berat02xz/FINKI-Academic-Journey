using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MovieApp.Migrations
{
    /// <inheritdoc />
    public partial class migrfix : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "EShopApplicationUserId",
                table: "TicketInOrders",
                type: "nvarchar(450)",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_TicketInOrders_EShopApplicationUserId",
                table: "TicketInOrders",
                column: "EShopApplicationUserId");

            migrationBuilder.AddForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_EShopApplicationUserId",
                table: "TicketInOrders",
                column: "EShopApplicationUserId",
                principalTable: "AspNetUsers",
                principalColumn: "Id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_TicketInOrders_AspNetUsers_EShopApplicationUserId",
                table: "TicketInOrders");

            migrationBuilder.DropIndex(
                name: "IX_TicketInOrders_EShopApplicationUserId",
                table: "TicketInOrders");

            migrationBuilder.DropColumn(
                name: "EShopApplicationUserId",
                table: "TicketInOrders");
        }
    }
}
