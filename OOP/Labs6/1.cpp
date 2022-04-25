// NBAPlayer
// Да се дефинира класа NBAPlayer за која ќе се чуваат:

// динамички алоцирана низа од карактери за името на играчот
// низа од максимум 40 карактери за тимот во кој играчот моментално настапува
// просечен број на поени на играчот оваа сезона (double)
// просечен број на асистенции на играчот оваа сезона (double)
// просечен број на скокови на играчот оваа сезона (double)
// За потребите на класата да се дефинираат:

// default конструктор и конструктор со аргументи
// copy constructor и оператор =
// деструктор
// метод rating() кој го враќа рејтингот на кошаркарот кој се пресметува како:

// 45% од поените + 30% од асистенциите + 25% од скоковите

// метод print() кој го печати играчот во следниот формат:

// Име - тим

// Points: поени

// Assists: асистенции

// Rebounds: скокови

// Rating: рејтинг

// AllStarPlayer
// Од претходната класа NBAPlayer да се изведе класата AllStarPlayer за која дополнително ќе се чуваат и:

// просечен број на поени на играчот од All Star натпреварите (double)
// просечен број на асистенции на играчот од All Star натпреварите (double)
// просечен број на скокови на играчот од All Star натпреварите (double)
// За потребите на класата да се дефинираат:

// default конструктор
// конструктор кој прима објект од NBAPlayer и плус додатните информации (погледни main)
// конструктор кој ги прима сите аргументи (погледни main)
// copy constructor, оператор =, деструктор
// метод allStarRating() кој го враќа рејтингот на кошаркарот од All Star натпреварите и кој се пресметува како:

// 30% од поените + 40% од асистенциите + 30% од скоковите

// Да се препокријат методите:

// rating() кој го враќа просекот од обичниот рејтинг на кошаркарот и неговиот All Star рејтинг
// print() кој покрај основните информации за кошаркарот печати и:

// All Star Rating: рејтингот од All Star натпреварите

// New Rating: просечниот рејтинг

// For example:

// Input	
// 1
// Nikola_Jokic Denver_Nuggets 18.3 6.1 10.6
// Lonzo_Ball Los_Angeles_Lakers 10.2 7.2 6.9
// Donovan_Mitchell Utah_Jazz 20.4 3.6 3.7
// Ben_Simmons Philadelphia_76ers 16 8.2 8.2
// Kristaps_Porzingis New_York_Knicks 22.7 1.2 6.6

// Result
// NBA PLAYERS:
// =====================================
// Nikola_Jokic - Denver_Nuggets
// Points: 18.3
// Assists: 6.1
// Rebounds: 10.6
// Rating: 12.715
// Lonzo_Ball - Los_Angeles_Lakers
// Points: 10.2
// Assists: 7.2
// Rebounds: 6.9
// Rating: 8.475
// Donovan_Mitchell - Utah_Jazz
// Points: 20.4
// Assists: 3.6
// Rebounds: 3.7
// Rating: 11.185
// Ben_Simmons - Philadelphia_76ers
// Points: 16
// Assists: 8.2
// Rebounds: 8.2
// Rating: 11.71
// Kristaps_Porzingis - New_York_Knicks
// Points: 22.7
// Assists: 1.2
// Rebounds: 6.6
// Rating: 12.225

int main() {

  char name[50];
  char team[40];
  double points;
  double assists;
  double rebounds;
  double allStarPoints;
  double allStarAssists;
  double allStarRebounds;

  NBAPlayer * players = new NBAPlayer[5];
  AllStarPlayer * asPlayers = new AllStarPlayer[5];
  int n;
  cin >> n;

  if (n == 1) {

    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i = 0; i < 5; ++i) {
      cin >> name >> team >> points >> assists >> rebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      players[i].print();
    }
  }
  else if (n == 2) {

    for (int i=0; i < 5; ++i){
      cin >> name >> team >> points >> assists >> rebounds;
      cin >> allStarPoints >> allStarAssists >> allStarRebounds;
      players[i] = NBAPlayer(name,team,points,assists,rebounds);
      asPlayers[i] = AllStarPlayer(players[i],allStarPoints,allStarAssists,allStarRebounds);
    }

    cout << "NBA PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      players[i].print();

    cout << "ALL STAR PLAYERS:" << endl;
    cout << "=====================================" << endl;
    for (int i=0; i < 5; ++i)
      asPlayers[i].print();

    }
    else if (n == 3) {

      for (int i=0; i < 5; ++i){
        cin >> name >> team >> points >> assists >> rebounds;
        cin >> allStarPoints >> allStarAssists >> allStarRebounds;
        asPlayers[i] = AllStarPlayer(name, team, points, assists, rebounds,
                                     allStarPoints,allStarAssists,allStarRebounds);
      }
      cout << "ALL STAR PLAYERS:" << endl;
      cout << "=====================================" << endl;
      for (int i=0; i < 5; ++i)
        asPlayers[i].print();

    }
    
    delete [] players;
    delete [] asPlayers;
}
