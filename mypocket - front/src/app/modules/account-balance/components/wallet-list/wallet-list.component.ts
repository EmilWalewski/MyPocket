import { Component, OnInit, ViewChild, ViewContainerRef, ViewChildren, QueryList, ElementRef, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { __spreadArrays } from 'tslib';

@Component({
  selector: 'app-wallet-list',
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.scss']
})
export class WalletListComponent implements OnInit{

  walletLists = [
    new Wal('Wallet 1', 30, false),
    new Wal('Wallet 2', 40, false),
    new Wal('Wallet 3', 50, true),
    new Wal('Wallet 4', 10.58, false),
    new Wal('Wallet 5', 5.90, false)
  ];

  isWalletSelected;

  private selectedWalletIndex;


  mainWallet;
  public mainWalletIndex;

  @ViewChildren('wallet') private wallets: QueryList<ElementRef>;

  constructor(private renderer: Renderer2, private router: Router) { }

  ngOnInit(): void {
    this.isWalletSelected = false;

    this.mainWallet = this.walletLists.filter((wal, index) => {
      if (wal.selected === true) {
        this.mainWalletIndex = index;
        return true;
      }
    })[0];

    // this.walletLists = this.walletLists.filter(wal => wal.selected !== true);
    // set selected wallet here -> data get from service
  }

  selectWallet(event: PointerEvent, id?) {
    this.selectedWalletIndex = id;
    event.stopPropagation();
    this.setOrResetSelectableListStyles(id);
  }

  selectMainWallet(event: PointerEvent) {
    this.selectWallet(event, this.mainWalletIndex);
  }

  setOrResetSelectableListStyles(id?) {

    console.log('wallet id: ' + id);
    if (id === undefined) {
      this.isWalletSelected = false;
      this.wallets.forEach(wallet => {
        this.renderer.setStyle(wallet.nativeElement.firstChild, 'background', 'rgb(243, 236, 236)');
      });
    } else {
      this.wallets.forEach(wallet => {
        this.renderer.setStyle(wallet.nativeElement.firstChild, 'background', 'rgb(243, 236, 236)');
      });
      this.renderer.setStyle((this.wallets.toArray()[id]).nativeElement.firstChild, 'background', 'rgb(158, 154, 190)');
      this.isWalletSelected = true;
    }
  }

  // ustawic cookie po stronie serwera z danymi usera i odczytac je podczas zapytania typu post

  setMainWallet() {
    // this.walletLists.push(this.mainWallet);
    // this.mainWallet = this.walletLists[this.selectedWalletIndex];

    this.walletLists.forEach((wal, index) => {
      if (index === this.selectedWalletIndex) {
        this.mainWallet = wal;
        this.mainWalletIndex = index;
        wal.selected = true;
      }
      else {
        wal.selected = false;
      }
    });

    this.walletLists = this.walletLists.sort((a, b) => {
      return a.name.localeCompare(b.name);
    });
    // this.walletLists = this.walletLists
    //   .filter(wal => wal !== this.mainWallet)
    //   .sort((a, b) => {
    //     return a.name.localeCompare(b.name);
    //   });


  }

  editWallet() {
    // get wallet from list service, so to retrive wallet id
    this.router.navigate(['wallet', this.selectedWalletIndex]);
  }
}

class Wal {

  constructor(public name, private amount, public selected: boolean) { }
}
