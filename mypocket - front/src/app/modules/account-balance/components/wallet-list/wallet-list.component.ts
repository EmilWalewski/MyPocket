import { Component, OnInit, ViewChild, ViewContainerRef, ViewChildren, QueryList, ElementRef, Renderer2, AfterViewInit, AfterContentInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Wallet, WalletService } from 'src/app/services/wallet/wallet.service';
import { __spreadArrays } from 'tslib';

@Component({
  selector: 'app-wallet-list',
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.scss']
})
export class WalletListComponent implements OnInit, OnDestroy {

  walletLists: Array<Wallet>;

  isWalletSelected;

  private selectedWalletIndex;


  mainWallet;
  public mainWalletIndex;

  @ViewChildren('wallet') private wallets: QueryList<ElementRef>;

  constructor(private renderer: Renderer2, private router: Router, private route: ActivatedRoute, private walletService: WalletService) { }

  ngOnInit(): void {
    this.walletService.getWallets().subscribe(wallets => { this.walletLists = wallets; this.init(); });
  }

  ngOnDestroy(): void {
    this.walletService.saveAllChanges(this.walletLists);
  }

  /**
   *
   *    init method
   *
   *
   */

  init() {



    this.isWalletSelected = false;

    console.log(this.walletLists);

    if (this.walletLists.length === 0) {
      alert('zero length');
      const t = { id: 0, name: 'Default', quantity: 0, isMainWallet: true };
      this.walletService.uploadWallet(t).subscribe(data => { });
      this.walletLists.push(t);
    }

    this.mainWallet = this.walletLists.filter((wal, index) => {
      if (wal.isMainWallet === true) {
        this.mainWalletIndex = index;
        return true;
      }
    })[0];

  }

  // ------------------------------------------------------------------------------



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


  setMainWallet() {

    this.walletLists.forEach((wal, index) => {
      if (index === this.selectedWalletIndex) {
        this.mainWallet = wal;
        this.mainWalletIndex = index;
        wal.isMainWallet = true;
      }
      else {
        wal.isMainWallet = false;
      }
    });

    this.walletLists = this.walletLists.sort((a, b) => {
      return a.name.localeCompare(b.name);
    });

    this.walletService.updateCashedWallets(this.walletLists);
  }

  editWallet() {
    // get wallet from list service, so to retrive wallet id
    // this.router.navigate(['wallet', this.selectedWalletIndex], { relativeTo: this.route });
    this.router.navigate(['wallet'], { queryParams: { index: this.selectedWalletIndex }, relativeTo: this.route });
  }
}

