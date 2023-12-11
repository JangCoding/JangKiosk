package main

// 타입이름(Burgurs, Drinks, Beer ... ) , 메뉴 아이템 리스트
class MenuType(val _name: String, val _items: List<MenuItem>) {
    var name = _name
    var items =_items
}