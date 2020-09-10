
export class Dictionary {

    constructor() {

        this.regex = new RegExp(this.datePattern, 'i');
        this.timeRegex = new RegExp(this.timePattern, 'i');
        this.priceRegex = new RegExp(this.pricePattern, 'i');

        this.dictionary = new Map<string, string>([

            ['"ARTUS"', 'Sklep Leviatan'],
            ['BIEDRONKA', 'Sklep Biedronka'],
            ['KONFORT', 'Sklep Komfort']

        ]);

    }

    private dictionary: Map<string, string>;
    // private datePattern = '([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))';
    private datePattern = '^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$';
    private timePattern = '^[0-9]{2}\:[0-9]{2}$';
    private pricePattern = '^([1-9]{1,2}|[1-9]0)\,(0[0-9]|[1-9][0-9])$';

    private productNamePattern = '^(?=[A-Z])[A-Z\/]{3,10}$';
    private amountPattern = '(?<=\d)[a-z]{3}';

    private regex: RegExp;
    private timeRegex: RegExp;
    private priceRegex: RegExp;

    private detectingPrice: boolean;

    recognizeShop(text: string) {

        return this.dictionary.has(text.toLocaleUpperCase());
    }

    getRecognizedShopName(text: string) {

        return this.dictionary.get(text.toLocaleUpperCase());
    }

    priceParser(price: string) {
        if (price === 'SUMA' || price === 'PLN') {
            this.detectingPrice = true;
        }
        if (this.detectingPrice) {
            if (this.priceRegex.test(price.trim())) {
                this.detectingPrice = false;
                return true;
            }
        }
        return false;
    }

    dateParser(date: string) {
        return this.regex.test(date.trim());
    }

    timeParser(date: string) {
        return this.timeRegex.test(date.trim());
    }

    getWord(word: string) {
        return this.dictionary.has(word);
    }
}

export interface OCRResponse {
    date: string;
    time: string;
    price: string;
}
