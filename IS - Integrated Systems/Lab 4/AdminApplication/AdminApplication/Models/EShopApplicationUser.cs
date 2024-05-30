namespace AdminApplication.Models
{
    public class EShopApplicationUser 
    {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Address { get; set; }
        public ShoppingCart? UserCart { get; set; }
        public virtual ICollection<Ticket>? MyProducts { get; set; }
    }
}
