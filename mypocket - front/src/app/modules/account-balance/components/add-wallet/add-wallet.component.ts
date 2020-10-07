import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Wallet, WalletResponse, WalletService } from 'src/app/services/wallet/wallet.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-wallet',
  templateUrl: './add-wallet.component.html',
  styleUrls: ['./add-wallet.component.scss']
})
export class AddWalletComponent implements OnInit, OnDestroy {

  walletFormGroup: FormGroup;

  walletSubscription: Subscription;

  constructor(private formBuilder: FormBuilder, private walletService: WalletService,
    private router: Router, private route: ActivatedRoute) {
    this.walletFormGroup = this.createAddWalletForm(formBuilder);
  }

  ngOnInit(): void {
    if (this.route.snapshot.queryParamMap.has.length > 0) {

      this.walletSubscription = this.walletService.getWallet(+this.route.snapshot.queryParamMap.get('index'))
        .subscribe(wallet => {
          console.log(wallet);
          this.walletFormGroup.controls['id'].setValue(wallet.id ? wallet.id : 0);
          this.walletFormGroup.controls['name'].setValue(wallet.name);
          this.walletFormGroup.controls['quantity'].setValue(wallet.quantity);
          this.walletFormGroup.controls['isMainWallet'].setValue(wallet.isMainWallet ? wallet.isMainWallet : false);
        });
    }
  }

  ngOnDestroy(): void {
    if (this.walletSubscription !== undefined) {
      this.walletSubscription.unsubscribe();
    }
  }

  createAddWalletForm(builder: FormBuilder) {
    return builder.group({
      id: '',
      name: '',
      quantity: '',
      isMainWallet: 'false'
    });
  }

  onSubmit() {
    const result: WalletResponse = Object.assign({}, this.walletFormGroup.value);
    let errors = '';
    this.walletService.uploadWallet(result)
      .subscribe(data => {
        
        this.router.navigate(['../'], { relativeTo: this.route });
      },
        error => {
          console.log(error);
        }
      );
  }


}
