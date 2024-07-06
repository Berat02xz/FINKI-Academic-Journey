namespace MusicStore.Domain.DTO
{
    public class AddToCartDTO
    {
        public Guid SelectedProductId { get; set; }
        public string? SelectedProductName { get; set; }
        public int Quantity { get; set; }
    }
}
