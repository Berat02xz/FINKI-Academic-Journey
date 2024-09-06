namespace ConcertShop.Models.Domain
{
    public class ConcertPlay
    {
        public Guid Id { get; set; }
        public string ConcertName { get; set; }
        public string ConcertPlace { get; set; }
        public int ConcertPrice { get; set; }
        public string ConcertDate { get; set; }
    }
}
