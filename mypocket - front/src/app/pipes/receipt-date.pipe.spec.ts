import { ReceiptDatePipe } from './receipt-date.pipe';

describe('ReceiptDatePipe', () => {
  it('create an instance', () => {
    const pipe = new ReceiptDatePipe();
    expect(pipe).toBeTruthy();
  });
});
