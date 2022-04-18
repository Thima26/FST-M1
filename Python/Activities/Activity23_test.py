import pytest

def test_sum(num_list):
    sum = 0
    for n in num_list:
        sum += n
    assert sum == 55